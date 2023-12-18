package be.ccfun.day16;

public enum Direction {

	UP(-1,0),

	LEFT(0,-1),

	DOWN(1,0),

	RIGHT(0,1);

	private int row;
	private int column;

	Direction(int row, int column) {
		this.row = row;
		this.column = column;
	}


	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}
