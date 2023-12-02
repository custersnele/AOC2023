package be.ccfun.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day2 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/2"));
		CubeSet original = new CubeSet("12 red, 13 green, 14 blue");
		int sum = 0;
		long totalPower = 0;
		for (String line: lines) {
			boolean possible = true;
			String[] split = line.split(":");
			Game game = new Game();
			for (String set: split[1].split(";")) {
				CubeSet cubeSet = new CubeSet(set.trim());
				if (!cubeSet.isPossibleFrom(original)) {
					possible = false;
				}
				game.addCubeSet(cubeSet);
			}
			CubeSet cubeSet = game.getMinCubeSet();
			totalPower += cubeSet.getPower();
			if (possible) {
				int gameId = Integer.parseInt(split[0].split(" ")[1].trim());
				sum += gameId;
			}
		}
		System.out.println(sum);
		System.out.println(totalPower);

	}


}
