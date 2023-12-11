package be.ccfun.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day11 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/11"));
		List<Galaxy> galaxies = new ArrayList<>();
		int y = 0;
		int actualY = 0;
		int count = 1;
		while (y < lines.size()) {
			int x = 0;
			int actualX = 0;
			while (x < lines.get(0).length()) {
				if (lines.get(y).charAt(x) == '#') {
					galaxies.add(new Galaxy(count++, actualX, actualY));
					System.out.println("add new galaxy");
				}
				int colSkip = adjacentEmptyCols(lines, x + 1);
				x+= colSkip == 0? 1 : colSkip;
				actualX += colSkip == 0? 1: colSkip * 1000000;
			}
			int rowSkip = adjacentEmptyRows(lines, y + 1);
			actualY += rowSkip == 0? 1 : rowSkip * 1000000;
			y += rowSkip == 0? 1 : rowSkip;
		}
		for (Galaxy galaxy : galaxies) {
			System.out.println(galaxy);
		}
		long totalDistance = 0;
		for (Galaxy galaxy : galaxies) {
			for (Galaxy otherGalaxy : galaxies) {
				if (galaxy.getNumber() != otherGalaxy.getNumber()) {
					System.out.println(galaxy);
					System.out.println(otherGalaxy);
					System.out.println("Distance: " + galaxy.distance(otherGalaxy));
					totalDistance += galaxy.distance(otherGalaxy);
				}
			}
		}
		System.out.println(totalDistance / 2);
	}

	public static int adjacentEmptyRows(List<String> lines, int currentRow) {
		if (currentRow >= lines.size()) {
			return 0;
		}
		int count = 0;
		while (currentRow < lines.size() && lines.get(currentRow).chars().filter(ch -> ch == '#').count() == 0) {
			count++;
			currentRow++;
		}
		return count;
	}

	public static int adjacentEmptyCols(List<String> lines, int currentCol) {
		if (currentCol >= lines.get(0).length()) {
			return 0;
		}
		int count = 0;
		int finalCurrentCol = currentCol;
		long countGalaxies = lines.stream().map(l -> l.charAt(finalCurrentCol)).filter(ch -> ch == '#').count();
		while (currentCol < lines.size() &&  countGalaxies == 0) {
			count++;
			currentCol++;
			int finalCurrentCol1 = currentCol;
			countGalaxies = lines.stream().map(l -> l.charAt(finalCurrentCol1)).filter(ch -> ch == '#').count();
		}
		return count;
	}

}
