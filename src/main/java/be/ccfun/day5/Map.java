package be.ccfun.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Map {
	private String from;
	private String to;
	private List<Range> ranges = new ArrayList<>();



	public Map(String from, String to) {
		this.from = from;
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public void addRange(Range range) {
		ranges.add(range);
	}

	public long translate(long value) {
		Optional<Range> optionalRange = ranges.stream().filter(r -> r.inRange(value)).findAny();
		return optionalRange.map(range -> range.map(value)).orElse(value);
	}
}
