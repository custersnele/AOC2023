package be.ccfun.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/15"));
		Map<Integer, Box> boxes = new HashMap<>();
		String[] split = lines.get(0).split(",");
		long sum = Arrays.stream(split).map(Hasher::calculateHash).mapToLong(l -> l).sum();
		System.out.println(sum);

		for (String data : split) {
			Lens lens = new Lens(data);
			Box box = boxes.get(lens.getHash());
			if (data.contains("=")) {
				if (box == null) {
					box = new Box(lens.getHash());
					boxes.put(lens.getHash(), box);
				}
				box.addLens(lens);
			} else if (box != null) {
				box.removeLens(lens);
			}
		}

		long total = 0;
		for (int i = 0; i < 256; i++) {
			Box aBox = boxes.get(i);
			if (aBox != null) {
				total += aBox.getFocusingPower();
			}

		}
		System.out.println(total);
	}

}
