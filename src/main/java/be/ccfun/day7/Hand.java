package be.ccfun.day7;

import be.ccfun.day4.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand> {

	private String cards;
	private int bid;

	public Hand(String cards, int bid) {
		this.cards = cards;
		this.bid = bid;
	}

	@Override
	public int compareTo(Hand o) {
		if (this.getHighestType().ordinal() == o.getHighestType().ordinal()) {
			for (int i = 0; i < cards.length(); i++) {
				if (cards.charAt(i) != o.cards.charAt(i)) {
					return getCardValue(o.cards.substring(i, i+1)) - getCardValue(this.cards.substring(i, i+1));
				}
			}
		}
		return this.getHighestType().ordinal() - o.getHighestType().ordinal();
	}

	public String getCards() {
		return cards;
	}

	public int getBid() {
		return bid;
	}

	public HandType getHighestType() {
		if (cards.contains("J")) {
			List<String> posibilities = new ArrayList<>(cards.chars().mapToObj(c -> (char)c).map(String::valueOf).filter(s -> !s.equals("J")).distinct().toList());
			if (posibilities.isEmpty()) {
				posibilities.add("A");
			}
			HandType lowest = HandType.HIGH_CARD;
			for (String pos : posibilities) {
				Hand newHand = new Hand(cards.replace("J", pos), bid);
				if (newHand.getType().ordinal() < lowest.ordinal()) {
					lowest = newHand.getType();
				}
			}
			return lowest;
		} else {
			return getType();
		}
	}

	public HandType getType() {
		long count = cards.chars().mapToObj(e -> (char) e).distinct().count();
		Map<Character, Long> collect = cards.chars().mapToObj(e -> (char) e).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
		Map.Entry<Character, Long> max = collect.entrySet().stream().max((o1, o2) -> Long.compare(o1.getValue(), o2.getValue())).get();
		if (count == 1) {
			return HandType.FIVE_OF_A_KIND;
		} else if (count == 2) {
			if (max.getValue() == 4) {
				return HandType.FOUR_OF_A_KIND;
			} else {
				return HandType.FULL_HOUSE;
			}
		} else if (count == 3) {
			if (collect.values().stream().filter(x -> x == 2L).count() == 2) {
				return HandType.TWO_PAIR;
			} else {
				return HandType.THREE_OF_A_KIND;
			}
		} else if (count == 4) {
			return HandType.ONE_PAIR;
		} else  {
			return HandType.HIGH_CARD;
		}
	}


	private static int getCardValue(String card) {
		return switch (card) {
			case "A" -> 14;
			case "K" -> 13;
			case "Q" -> 12;
			case "J" -> 1;
			case "T" -> 10;
			default -> Integer.parseInt(card);
		};
	}
}
