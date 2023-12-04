package be.ccfun.day4;

import java.util.List;
import java.util.stream.Stream;

public class Card {
	private List<Integer> winningNumbers;
	private List<Integer> myNumbers;
	private int number;
	private int amount;

	public Card(int number, List<Integer> winningNumbers, List<Integer> myNumbers) {
		this.winningNumbers = winningNumbers;
		this.myNumbers = myNumbers;
		this.amount = 1;
	}

	public Card(int number, String winningNumbers, String myNumbers) {
		this.winningNumbers = Stream.of(winningNumbers.split("\\s+")).map(Integer::parseInt).toList();
		this.myNumbers = Stream.of(myNumbers.split("\\s+")).map(Integer::parseInt).toList();
		this.number = number;
		this.amount = 1;
	}

	public long getScore() {
		return myNumbers.stream().filter(winningNumbers::contains).count();
	}

	public void addOne() {
		amount++;
	}

	public void add(int amount) {
		this.amount += amount;
	}

	public int getNumber() {
		return number;
	}

	public int getAmount() {
		return amount;
	}

	public double getPoints() {
		if (getScore() == 0) {
			return 0;
		}
		return Math.pow(2, getScore() - 1);
	}
}
