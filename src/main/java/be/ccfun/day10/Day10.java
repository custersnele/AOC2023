package be.ccfun.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day10 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/10"));
		Map map = new Map(lines);
		map.calculateDistances();
		map.print();
		System.out.println(map.maxDistance());
		List<List<Tile>> tiles = map.createTiles();
		for (List<Tile> tileList : tiles) {
			for (Tile tile: tileList) {
				tile.printTop();
			}
			System.out.println();
			for (Tile tile: tileList) {
				tile.printBottom();
			}
			System.out.println();
		}
		System.out.println(map.countEnclosedTiles());
	}
}
