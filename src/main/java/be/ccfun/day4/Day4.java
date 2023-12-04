package be.ccfun.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/4"));
		Map<Integer, Card> cards = new HashMap<>();
		for (int i = 1; i <= lines.size() ; i++) {
			String[] data = lines.get(i-1).split(":");
			Card card = new Card(i, data[1].split("\\|")[0].trim(), data[1].split("\\|")[1].trim());
			cards.put(i, card);
		}
		for (int i = 1; i <= lines.size() ; i++) {
			Card card = cards.get(i);
			for (int j = 1; j <= card.getScore(); j++) {
				cards.get(i + j).add(card.getAmount());
			}
		}
		long total = cards.values().stream().mapToLong(Card::getAmount).sum();
		System.out.println(total);

	}

}
