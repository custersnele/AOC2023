package be.ccfun.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day6 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/6"));
		List<Integer> times = Arrays.stream(lines.get(0).replace("Time: ", "").trim().split(" ")).filter(s -> !s.isBlank()).map(Integer::parseInt).toList();
		List<Integer> distances = Arrays.stream(lines.get(1).replace("Distance: ", "").trim().split(" ")).filter(s -> !s.isBlank()).map(Integer::parseInt).toList();
		long result = 1;
		for (int race = 0; race < times.size(); race++) {
			int dist = distances.get(race);
			int waysToWin = 0;
			for (int t = 1; t <= times.get(race); t++) {
				long d = raceDistance(times.get(race) - t, t);
				if (d > dist) {
					waysToWin++;
				}
				System.out.println(t + " " + d);
			}
			System.out.println("WaysToWin " + waysToWin);
			result *= waysToWin;
		}
		System.out.println(result);
		System.out.println("RACE 2");
		long time = Long.parseLong(lines.get(0).replace("Time: ", "").replace(" ", "").trim());
		long distance = Long.parseLong(lines.get(1).replace("Distance: ", "").replace(" ", "").trim());
		int waysToWin = 0;
		for (long t = 1; t <= time; t++) {
			long d = raceDistance(time - t, t);
			if (d > distance) {
				waysToWin++;
			}
			//System.out.println(t + " " + d);
		}
		System.out.println(waysToWin);

	}

	public static long raceDistance(long time, long speed) {
		return time * speed;
	}

}
