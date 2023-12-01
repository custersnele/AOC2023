package be.ccfun.common;

import com.google.common.net.HttpHeaders;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Fetches puzzle inputs using an in-memory cache, the local disk, or the Advent of Code site.
 */
public class PuzzleInputFetcher {

    private final Map<Integer, String> _cache = new ConcurrentHashMap<>();
    private final OkHttpClient _httpClient = new OkHttpClient();
    private final Path _puzzleStorePath;
    private final Path _sessionTokenPath;

    private String _sessionToken;

    public PuzzleInputFetcher() {
        this(Path.of("puzzle"), Path.of("cookie.txt"));
    }

    PuzzleInputFetcher(Path puzzleStorePath, Path sessionTokenPath) {
        _puzzleStorePath = puzzleStorePath;
        _sessionTokenPath = sessionTokenPath;
    }

    /**
     * Fetches the given day's puzzle input. It will try to fetch first from the in-memory cache, then the local disk,
     * and finally the Advent of Code site (storing the input to the local disk to avoid further fetches).
     *
     * @param day the puzzle's day
     * @return the puzzle input
     * @throws RuntimeException if the puzzle cannot be fetched
     */
    public String getPuzzleInput(int year, int day) {
        return _cache.computeIfAbsent(day, s -> {
            try {
                try {
                    return fetchLocalPuzzleInput(day);
                } catch (IOException e) {
                    System.out.println("Unable to fetch puzzle input from local store for day " + day);
                }

                String input;
                try {
                    input = fetchRemotePuzzleInput(year, day);
                } catch (IOException e) {
                    System.out.println("Unable to fetch puzzle input from remote store for day " + day);
                    throw e;
                }

                try {
                    storePuzzleInputLocally(day, input);
                } catch (IOException e) {
                    System.out.println("Unable to store puzzle input locally for day " + day);

                }
                return input;
            } catch (IOException e) {
                throw new RuntimeException("Couldn't get puzzle input for day " + day);
            }
        });
    }

    String fetchLocalPuzzleInput(int day) throws IOException {
        return Files.readString(_puzzleStorePath.resolve(String.valueOf(day)));
    }

    void storePuzzleInputLocally(int day, String puzzleInput) throws IOException {
        Files.createDirectories(_puzzleStorePath);
        var path = _puzzleStorePath.resolve(String.valueOf(day));
        Files.writeString(path, puzzleInput);
    }

    String fetchRemotePuzzleInput(int year, int day) throws IOException {
        var request = new Request.Builder()
                .url(getRemotePuzzleInputUrl(year, day))
                .header(HttpHeaders.COOKIE, "session=" + getSessionToken())
                .get()
                .build();
        try (var response = _httpClient.newCall(request).execute()) {
            if (response.code() != 200) {
                throw new IOException("Request was not successful. Status code = " + response.code());
            }
            var body = response.body();
            if (body == null) {
                throw new IOException("Request body was empty");
            }
            return body.string();
        }
    }

    HttpUrl getRemotePuzzleInputUrl(int year, int day) {
        return HttpUrl.get("https://adventofcode.com/" + year +"/day/" + day + "/input");
    }

    synchronized String getSessionToken() throws IOException {
        try {
            if (_sessionToken == null) {
                _sessionToken = Files.readString(_sessionTokenPath).trim();
            }
            return _sessionToken;
        } catch (IOException e) {
            throw new IOException("Couldn't get session data from cookie.txt", e);
        }
    }
}
