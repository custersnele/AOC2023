package be.ccfun.day19;

public class Range {
	private int min;
	private int max;

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int size() {
		return max - min + 1;
	}
}
