package be.ccfun.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day13 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/13"));
		List<String> pattern = new ArrayList<>();
		long sumHorizontal = 0;
		long sumVertical = 0;
		for (String line : lines) {
			if (line.isBlank()) {
				Pattern pat = new Pattern(pattern);
				Reflection reflectionPoint = pat.findSmudgeReflection();
				if (reflectionPoint == null) {
					System.out.println(pat);
					reflectionPoint = pat.findSmudgeReflection();
				}
				if (reflectionPoint.getType() == Type.VERTICAL) {
					sumVertical += reflectionPoint.getPoint();
				} else {
					sumHorizontal += reflectionPoint.getPoint();
				}
				System.out.println(reflectionPoint);
				pattern = new ArrayList<>();
			} else {
				pattern.add(line);
			}
		}
		Pattern pat = new Pattern(pattern);
		System.out.println(pat.findSmudgeReflection());
		Reflection reflectionPoint = pat.findSmudgeReflection();
		if (reflectionPoint.getType() == Type.VERTICAL) {
			sumVertical += reflectionPoint.getPoint();
		} else {
			sumHorizontal += reflectionPoint.getPoint();
		}
		long total = sumVertical + (sumHorizontal * 100);
		System.out.println(total);
	}
}
