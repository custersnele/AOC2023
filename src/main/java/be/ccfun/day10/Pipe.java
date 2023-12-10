package be.ccfun.day10;

import java.util.Arrays;
import java.util.Optional;

public enum Pipe {
	VERTICAL("|", Direction.N, Direction.S),
	HORIZONTAL("-", Direction.E, Direction.W),
	BEND_N_E("L", Direction.N, Direction.E),
	BEND_N_W("J", Direction.N, Direction.W),
	BEND_S_W("7", Direction.S, Direction.W),
	BEND_S_E("F", Direction.S, Direction.E);

	private String symbol;
	private Direction from;
	private Direction to;

	Pipe(String symbol, Direction from, Direction to) {
		this.symbol = symbol;
		this.from = from;
		this.to = to;
	}

	public String getSymbol() {
		return symbol;
	}

	public Direction getFrom() {
		return from;
	}

	public Direction getTo() {
		return to;
	}

	public static Optional<Pipe> bySymbol(String symbol) {
		if (symbol.equals("S")) {
			return Optional.of(BEND_S_E); // TODO give correct S pipe.
			//return Optional.of(BEND_S_W); // TODO give correct S pipe.
		}
		return Arrays.stream(Pipe.values()).filter(p -> p.symbol.equals(symbol)).findAny();
	}

	public boolean hasDirection(Direction direction) {
		return from == direction || to == direction;
	}

	public Direction getOtherDirection(Direction direction) {
		if (from == direction) {
			return to;
		}
		return from;
	}

	public static boolean connects(Pipe tile1, Direction direction, Pipe tile2) {
		if (!tile1.hasDirection(direction)) {
			return false;
		}
		return tile2.hasDirection(Direction.getOpposite(direction));
	}
}
