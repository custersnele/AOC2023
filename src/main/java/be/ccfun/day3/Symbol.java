package be.ccfun.day3;

import java.util.Objects;

public class Symbol {
	private String symbol;
	private int row;
	private int col;

	public Symbol(String symbol, int row, int col) {
		this.symbol = symbol;
		this.row = row;
		this.col = col;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Symbol symbol1 = (Symbol) o;

		if (row != symbol1.row) {
			return false;
		}
		if (col != symbol1.col) {
			return false;
		}
		return Objects.equals(symbol, symbol1.symbol);
	}

	@Override
	public int hashCode() {
		int result = symbol != null ? symbol.hashCode() : 0;
		result = 31 * result + row;
		result = 31 * result + col;
		return result;
	}

	@Override
	public String toString() {
		return symbol;
	}

	public boolean isStar() {
		return "*".equals(symbol);
	}
}
