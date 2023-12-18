package be.ccfun.day18;

public enum Direction {
	RIGHT(0, 1),
	DOWN(1, 0),
	LEFT(0,-1),
	UP(-1, 0);

	private int deltaRow;
	private int deltaCol;

	Direction(int deltaRow, int deltaCol) {
		this.deltaRow = deltaRow;
		this.deltaCol = deltaCol;
	}

	public int getDeltaCol() {
		return deltaCol;
	}

	public int getDeltaRow() {
		return deltaRow;
	}

	public static Position getDirection(Position original, Direction direction) {
		return new Position(original.row() + direction.deltaRow, original.col() + direction.deltaCol);
	}

	public static Direction fromString(String pos) {
		for (Direction p : Direction.values()) {
			if (p.name().startsWith(pos)) {
				return p;
			}
		}
		return null;
	}
}
