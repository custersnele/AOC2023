package be.ccfun.day11;

import java.util.Map;

public class Galaxy {
	private int number;
	private long x;
	private long y;

	public Galaxy(int number, int x, int y) {
		this.number = number;
		this.x = x;
		this.y = y;
	}

	public int getNumber() {
		return number;
	}

	public long distance(Galaxy other) {
		return Math.abs(other.x - x) + Math.abs(other.y - y);
	}


	@Override
	public String toString() {
		return "Galaxy{" +
				"number=" + number +
				", x=" + x +
				", y=" + y +
				'}';
	}
}
