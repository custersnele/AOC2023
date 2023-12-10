package be.ccfun.day10;

public enum Direction {
	N(-1, 0),
	E(0, 1),
	S(1, 0),
	W(0,-1);

	private int deltaRow;
	private int deltaCol;

	Direction(int deltaRow, int deltaCol) {
		this.deltaRow = deltaRow;
		this.deltaCol = deltaCol;
	}

	public static Position getDirection(Position original, Direction direction) {
		return new Position(original.row() + direction.deltaRow, original.col() + direction.deltaCol);
	}

	public static Direction getOpposite(Direction direction) {
		return switch (direction) {
			case N -> S;
			case S -> N;
			case E -> W;
			case W -> E;
		};
	}
}
