package be.ccfun.day5;

public class Range {
	private long to;
	private long from;
	private long range;

	public Range(long to, long from, long range) {
		this.to = to;
		this.from = from;
		this.range = range;
	}

	public boolean inRange(long value) {
		return value >= from && value < from + range;
	}


	public long map(long value) {
		long dist = value - from;
		return to + dist;
	}


}
