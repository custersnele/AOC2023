package be.ccfun.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Mirror {
	SPLITTER_1("-"),
	SPLITTER_2("|"),
	REFLECTOR_1("/"),
	REFLECTOR_2("\\");

	private String symbol;

	Mirror(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public static Mirror getMirror(String symbol) {
		return Arrays.stream(Mirror.values())
				.filter(mirror -> mirror.symbol.equals(symbol))
				.findFirst()
				.orElse(null);
	}

	public static List<Direction> getNewDirections(Direction currentDirection, Mirror mirror) {
		switch (mirror) {
			case SPLITTER_1:
				return switch (currentDirection) {
					case Direction.UP, Direction.DOWN -> List.of(Direction.RIGHT, Direction.LEFT);
					case Direction.RIGHT, Direction.LEFT -> List.of(currentDirection);
				};
			case SPLITTER_2:
				return switch (currentDirection) {
					case Direction.UP, Direction.DOWN -> List.of(currentDirection);
					case Direction.RIGHT, Direction.LEFT -> List.of(Direction.UP, Direction.DOWN);
				};
			case REFLECTOR_1:
				return switch (currentDirection) {
					case UP -> List.of(Direction.RIGHT);
					case DOWN -> List.of(Direction.LEFT);
					case LEFT -> List.of(Direction.DOWN);
					case RIGHT -> List.of(Direction.UP);
				};
			case REFLECTOR_2:
				return switch (currentDirection) {
					case UP -> List.of(Direction.LEFT);
					case DOWN -> List.of(Direction.RIGHT);
					case LEFT -> List.of(Direction.UP);
					case RIGHT -> List.of(Direction.DOWN);
				};

		}
		return new ArrayList<>();
	}

}
