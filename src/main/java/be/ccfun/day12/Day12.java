package be.ccfun.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day12 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/12"));
		long sum = 0;
		for (String line : lines) {
			String[] split = line.split(" ");
			ConditionRecord conditionRecord = new ConditionRecord(split[0], Arrays.stream(split[1].split(",")).mapToInt(Integer::parseInt).toArray());
			System.out.println("RECORD:" + conditionRecord.getCondition());
			sum += conditionRecord.getArrangements();
		}
		System.out.println(sum);

		sum = 0;
		for (String line : lines) {
			String[] split = line.split(" ");
			String part1 = split[0] + "?" + split[0] + "?" +split[0] + "?" + split[0] + "?"+ split[0];
			String part2 = split[1] + "," + split[1] + "," + split[1] + "," + split[1] + "," + split[1];
			ConditionRecord conditionRecord = new ConditionRecord(part1, Arrays.stream(part2.split(",")).mapToInt(Integer::parseInt).toArray());
			System.out.println("RECORD:" + conditionRecord.getCondition());
			sum += conditionRecord.getArrangements();
		}
		System.out.println(sum);
	}

}
