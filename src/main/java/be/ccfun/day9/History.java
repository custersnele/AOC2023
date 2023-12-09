package be.ccfun.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class History {
	private List<Long> numbers;

	public History(String numbers) {
		this.numbers = Arrays.stream(numbers.trim().split(" ")).map(Long::parseLong).toList();
	}

	public long extrapolate() {
		List<Long> row = new ArrayList<>(numbers);
		List<Long> diffs = new ArrayList<>();
		diffs.add(row.getLast());
		while (row.stream().distinct().count() != 1) {
			List<Long> finalRow = new ArrayList<>(row);
			row = IntStream.range(0, row.size() - 1).mapToObj(i -> finalRow.get(i + 1) - finalRow.get(i)).toList();
			diffs.add(row.getLast());
		}
		return diffs.stream().mapToLong(i -> i).sum();
	}

	public long extrapolateRev() {
		List<Long> row = new ArrayList<>(numbers);
		List<Long> diffs = new ArrayList<>();
		diffs.add(row.getFirst());
		while (row.stream().distinct().count() != 1) {
			List<Long> finalRow = new ArrayList<>(row);
			row = IntStream.range(0, row.size() - 1).mapToObj(i -> finalRow.get(i + 1) - finalRow.get(i)).toList();
			diffs.add(row.getFirst());
		}
		long result = diffs.getLast();
		for (int i = diffs.size() - 2; i >= 0; i--) {
			result = diffs.get(i) - result;
		}
		return result;
	}

	@Override
	public String toString() {
		return "History{" +
				"numbers=" + numbers +
				'}';
	}
}
