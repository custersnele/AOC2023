package be.ccfun.day9;

import be.ccfun.day8.CamelMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day9 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/9"));

		long sum = 0;
		long sumRev = 0;
		for (String line : lines) {
			History history = new History(line);
			long extrapolate = history.extrapolate();
			long extrapolateRev = history.extrapolateRev();
			sum += extrapolate;
			sumRev += extrapolateRev;
			System.out.println(history + " " + extrapolate + " " + extrapolateRev);
		}
		System.out.println(sum);
		System.out.println(sumRev);


	}

}
