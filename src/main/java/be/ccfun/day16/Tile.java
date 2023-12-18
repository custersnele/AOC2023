package be.ccfun.day16;


import java.util.ArrayList;
import java.util.List;

public class Tile {
	private int row;
	private int column;
	private boolean energized = false;
	private List<Direction> energizedDirections = new ArrayList<>();
	private Mirror mirror;

	public Tile(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public Tile(Tile original) {
		this.row = original.row;
		this.column = original.column;
		this.mirror = original.mirror;
	}

	public void setMirror(Mirror mirror) {
		this.mirror = mirror;
	}

	public boolean isEnergized() {
		return energized;
	}

	public boolean isEnergized(Direction direction) {
		return isEnergized() && energizedDirections.contains(direction);
	}

	public List<Energize> energize(Direction currentDirection, List<Tile> tiles) {
		energized = true;
		energizedDirections.add(currentDirection);

		if (mirror == null) { // goes further in currenct direction
			Tile next = getTile(row + currentDirection.getRow(), column + currentDirection.getColumn(), tiles);
			if (next != null) {
				return List.of(new Energize(next, currentDirection));
			} else {
				return List.of();
			}
		} else { // changes direction
			List<Direction> newDirections = Mirror.getNewDirections(currentDirection, mirror);
			List<Energize> nextEnergizes = new ArrayList<>();
			for (Direction newDirection : newDirections) {
				Tile next = getTile(row + newDirection.getRow(), column + newDirection.getColumn(), tiles);
				if (next != null) {
					nextEnergizes.add(new Energize(next, newDirection));
				}
			}
			return nextEnergizes;
		}
	}

	public static Tile getTile(int row, int column, List<Tile> tiles) {
		return tiles.stream()
				.filter(tile -> tile.row == row && tile.column == column)
				.findFirst()
				.orElse(null);
	}
}
