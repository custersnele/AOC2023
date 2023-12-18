package be.ccfun.day16;

import be.ccfun.day15.Box;
import be.ccfun.day15.Hasher;
import be.ccfun.day15.Lens;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/16"));
		List<Tile> tiles = new ArrayList<>();
		for (int row = 0; row < lines.size(); row++) {
			for (int col = 0; col < lines.get(0).length(); col++) {
				Tile tile = new Tile(row, col);
				tile.setMirror(Mirror.getMirror(lines.get(row).substring(col, col + 1)));
				tiles.add(tile);
			}
		}
		long maxCount = 0;
		for (int row  = 0; row < lines.size(); row++) {
			if (row == 0 || row == lines.size() - 1) {
				for (int col = 0; col < lines.get(0).length(); col++) {
					List<Tile> copyTiles = deepCopy(tiles);
					long countEnergy = countEnergy(Tile.getTile(row, col, copyTiles), copyTiles, row == 0 ? Direction.DOWN : Direction.UP);
					if (countEnergy > maxCount) {
						maxCount = countEnergy;
					}
				}
			}
			List<Tile> copyTiles = deepCopy(tiles);
			long countEnergy = countEnergy(Tile.getTile(row, 0, copyTiles), copyTiles, Direction.RIGHT);
			System.out.println(countEnergy);
			if (countEnergy > maxCount) {
				maxCount = countEnergy;
			}
			copyTiles = deepCopy(tiles);
			countEnergy = countEnergy(Tile.getTile(row, lines.get(0).length() - 1, copyTiles), copyTiles, Direction.LEFT);
			if (countEnergy > maxCount) {
				maxCount = countEnergy;
			}

		}
		System.out.println(maxCount);

	}

	private static List<Tile> deepCopy(List<Tile> tiles) {
		List<Tile> copyTiles = new ArrayList<>();
		for (Tile tile : tiles) {
			copyTiles.add(new Tile(tile));
		}
		return copyTiles;
	}

	public static long countEnergy(Tile firstTile, List<Tile> tiles, Direction direction) {
		List<Energize> next = firstTile.energize(direction, tiles);
		while (!next.isEmpty()) {
			List<Energize> nextNext = new ArrayList<>();
			for (Energize energize : next) {
				if (!energize.tile().isEnergized(energize.direction())) {
					nextNext.addAll(energize.tile().energize(energize.direction(), tiles));
				}
			}
			next = nextNext;
		}
		return tiles.stream().filter(Tile::isEnergized).count();
	}

}
