package be.ccfun.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day7 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/7johan"));
		List<Hand> hands = new ArrayList<>();
		for (String line : lines) {
			String[] split = line.split(" ");
			hands.add(new Hand(split[0], Integer.parseInt(split[1])));
		}
		List<Hand> sortedHands = hands.stream().sorted(Comparator.reverseOrder()).toList();

		long total = 0;
		for (int i = 0; i < sortedHands.size(); i++) {
			Hand hand = sortedHands.get(i);
			System.out.println(hand.getCards() + " " + hand.getHighestType());
			total += ((long) hand.getBid() * (i + 1));
		}
		System.out.println(total);



	}

}
