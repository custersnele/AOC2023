package be.ccfun.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day1 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/1"));
		System.out.println(replaceWord("eightwo"));
		int sum = lines.stream().map(Day1::replaceWord)
				.peek(System.out::println)
				.mapToInt(l -> getNumber(l))
				.sum();
		System.out.println(sum);

	}

	public static String replaceWord(String s) {
		int ix = 0;
		StringBuilder newS = new StringBuilder();
		while (ix < s.length()) {
			List<Integer> result = replaceWord(s, ix);
			if (result.isEmpty()) {
				newS.append(s.charAt(ix));
				ix++;
			} else {
				newS.append(result.get(0));
				ix = ix + result.get(1);
			}
		}
		return newS.toString();
	}

	public static List<Integer> replaceWord(String word, int ix) {
		List<String> words = Arrays.asList("one", "two", "three", "four", "five", "six",
				"seven", "eight", "nine");
		for (int i = 0; i < words.size(); i++) {
			if (word.substring(ix).startsWith(words.get(i))) {
				return Arrays.asList(i + 1, words.get(i).length() - 1);
			}
		}
		return Collections.emptyList();

	}

	public static int getNumber(String s) {
		List<Integer> digits = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				digits.add(Integer.parseInt(s.substring(i, i+1)));
			}
		}
		int x = Integer.parseInt(String.valueOf(digits.get(0)) + String.valueOf(digits.get(digits.size() - 1)));
		System.out.println(x);
		return Integer.parseInt(String.valueOf(digits.get(0)) + String.valueOf(digits.get(digits.size() - 1)));
	}
}
