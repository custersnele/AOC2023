package be.ccfun.day10;

public class Tile {
	private boolean topRight = false;
	private boolean topLeft = false;
	private boolean bottomRight = false;
	private boolean bottomLeft = false;

	public Tile(boolean topLeft, boolean topRight, boolean bottomLeft, boolean bottomRight) {
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomRight = bottomRight;
		this.bottomLeft = bottomLeft;
	}

	public Tile() {
	}

	public Tile next(Pipe pipe) {
		return switch (pipe) {
			case VERTICAL -> new Tile(topRight, !topRight, topRight, !topRight);
			case HORIZONTAL -> new Tile(topRight, topRight, !topRight, !topRight);
			case BEND_N_E -> new Tile(topRight, !topRight, topRight, topRight);
			case BEND_S_W -> new Tile(topRight, topRight, !topRight, topRight);
			case BEND_N_W -> new Tile(topRight, !topRight, !topRight, !topRight);
			case BEND_S_E -> new Tile(topRight, topRight, topRight, !topRight);
		};
	}

	public Tile next() {
		return new Tile(topRight, topRight, topRight, topRight);
	}

	public void printTop() {
		System.out.print(topLeft? "X":"O");
		System.out.print(topRight? "X":"O");
	}

	public void printBottom() {
		System.out.print(bottomLeft? "X":"O");
		System.out.print(bottomRight? "X":"O");
	}


	public boolean isInside() {
		return topLeft || topRight || bottomLeft || bottomRight;
	}
}
