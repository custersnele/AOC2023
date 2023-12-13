package be.ccfun.day13;

import java.util.List;
import java.util.stream.Collectors;

public class Pattern {

	private List<String> pattern;

	public Pattern(List<String> pattern) {
		this.pattern = pattern;
	}

	public String getColumn(int col) {
		return pattern.stream().map(s -> s.substring(col, col + 1)).collect(Collectors.joining());
	}


	public boolean isHorizontalReflection(int row) {
		int i = 0;
		while (row - i >= 0 && row + i + 1 < pattern.size()) {
			if (!pattern.get(row - i).equals(pattern.get(row + i + 1))) {
				return false;
			}
			i++;
		}
		return true;
	}

	public boolean isVerticalReflection(int col) {
		int i = 0;
		while (col - i >= 0 && col + i + 1 < pattern.get(0).length()) {
			if (!getColumn(col - i).equals(getColumn(col + i + 1))) {
				return false;
			}
			i++;
		}
		return true;
	}

	public Reflection getReflectionPoint(Reflection exclude) {
		for (int i = 0; i < pattern.size() - 1; i++) {
			if (pattern.get(i).equals(pattern.get(i + 1))) {
				if (isHorizontalReflection(i) && (exclude == null || (exclude.getType() != Type.HORIZONTAL || exclude.getPoint() != i + 1))) {
					return new Reflection(i + 1, Type.HORIZONTAL);
				}
			}
		}
		for (int i = 0; i < pattern.get(0).length() - 1; i++) {
			if (getColumn(i).equals(getColumn(i + 1))) {
				if (isVerticalReflection(i) && ( exclude == null || (exclude.getType() != Type.VERTICAL || exclude.getPoint() != i + 1))) {
					return new Reflection(i + 1, Type.VERTICAL);
				}
			}
		}
		return null;
	}


	public Reflection findSmudgeReflection() {
		Reflection original = getReflectionPoint(null);
		for (int curRow = 0; curRow < pattern.size(); curRow++) {
			for (int curCol = 0; curCol < pattern.get(0).length(); curCol++) {
				String c = pattern.get(curRow).substring(curCol, curCol + 1);
				String element = pattern.get(curRow);
				pattern.set(curRow, element.substring(0, curCol) + (c.equals("#") ? ".": "#") + element.substring(curCol + 1));
				Reflection newReflectionPoint = getReflectionPoint(original);
				pattern.set(curRow, element.substring(0, curCol) + c + element.substring(curCol + 1));
				if (newReflectionPoint != null && !original.equals(newReflectionPoint)) {
					return newReflectionPoint;
				}
			}
		}
		return null;
	}
}
