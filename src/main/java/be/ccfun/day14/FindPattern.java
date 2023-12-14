package be.ccfun.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FindPattern {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/find_pattern.csv"));
		String[] split = lines.get(0).split(";");
		int t = Integer.parseInt(split[0]);
		List<Integer> cycle = new ArrayList<>();
		for (String line : lines) {
			String[] split2 = line.split(";");
			int value = Integer.parseInt(split2[1]);
			cycle.add(value);
		}
		int currIdx = 0;
		// 1000000000
		for (int i = t; i <= 1000000000; i++) {
			if (i % 10000 == 0 || i > 999990000) {
				System.out.println(i + ";" + cycle.get(currIdx));
			}
			currIdx++;
			if (currIdx >= cycle.size()) {
				currIdx = 0;
			}
		}


	}
}
