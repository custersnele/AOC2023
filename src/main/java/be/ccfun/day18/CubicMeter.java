package be.ccfun.day18;

public class CubicMeter {
	private Position position;
	private String color;

	public CubicMeter(Position position, String color) {
		this.position = position;
		this.color = color;
	}

	public CubicMeter(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public int getRow() {
		return position.row();
	}

	public int getCol() {
		return position.col();
	}

	public String getColor() {
		return color;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		CubicMeter that = (CubicMeter) o;

		return position.equals(that.position);
	}

	@Override
	public int hashCode() {
		return position.hashCode();
	}
}
