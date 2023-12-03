package be.ccfun.day3;

public class EngineElement {
	private int number;
	private Symbol symbol;

	public EngineElement(int number, Symbol symbol) {
		this.number = number;
		this.symbol = symbol;
	}

	public boolean isStar() {
		return symbol != null && symbol.isStar();
	}

	public Symbol getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return number + (symbol != null? symbol.toString() : " ");
	}

	public int getNumber() {
		return number;
	}

	public boolean isPart() {
		return symbol != null;
	}
}
