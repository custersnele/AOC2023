package be.ccfun.day3;

import java.util.List;
import java.util.Optional;

public enum Direction {
	N(-1, 0),
	NE(-1, 1),
	E(0,1),
	SE(1,1),
	S(1,0),
	SW(1,-1),
	W(0,-1),
	NW(-1, -1);

	private int deltaRow;
	private int deltaCol;

	Direction(int deltaRow, int deltaCol) {
		this.deltaRow = deltaRow;
		this.deltaCol = deltaCol;
	}

	public static String getElement(Direction direction, int currentRow, int currentCol, List<String> schema) {
		int row = currentRow + direction.deltaRow;
		int col = currentCol + direction.deltaCol;
		if (row >= 0 && row < schema.size() && col >=0 && col < schema.get(0).length()) {
			return schema.get(row).substring(col, col + 1);
		} else {
			return "";
		}
	}

	public static Optional<Symbol> getAdjacentSymbol(int currentRow, int currentCol, List<String> schema) {
		for (Direction direction: Direction.values()) {
			String element = getElement(direction, currentRow, currentCol, schema);
			if (!".".equals(element) && !element.isEmpty() && !Character.isDigit(element.charAt(0))) {
				return Optional.of(new Symbol(element, currentRow + direction.deltaRow, currentCol + direction.deltaCol));
			}
		}
		return Optional.empty();
	}
}
