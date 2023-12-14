package be.ccfun.day14;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dish {
	private List<String> lines;

	public Dish(List<String> lines) {
		this.lines = lines;
	}

	public void tiltNorth() {
		List<String> temp = new ArrayList<>();
		List<String> result = new ArrayList<>();
		for (int col = 0; col < lines.get(0).length() ; col++) {
			String column = getColumn(col);
			String newColumn = tiltColumn(column);
			temp.add(newColumn);
		}
		for (int col = 0; col < temp.get(0).length() ; col++) {
			String column = getColumn(temp, col);
			result.add(column);
		}
		lines = result;
	}

	public void tiltWest() {
		List<String> result = new ArrayList<>();
		for (int row = 0; row < lines.get(0).length() ; row++) {
			String line = lines.get(row);
			String newRow = tiltColumn(line);
			result.add(newRow);
		}
		lines = result;
	}

	public void tiltSouth() {
		List<String> temp = new ArrayList<>();
		List<String> result = new ArrayList<>();
		for (int col = 0; col < lines.get(0).length() ; col++) {
			String column = new StringBuilder(getColumn(col)).reverse().toString();
			String newColumn = new StringBuilder(tiltColumn(column)).reverse().toString();
			temp.add(newColumn);
		}
		for (int col = 0; col < temp.get(0).length() ; col++) {
			String column = getColumn(temp, col);
			result.add(column);
		}
		lines = result;
	}

	public void tiltEast() {
		List<String> result = new ArrayList<>();
		for (int row = 0; row < lines.get(0).length() ; row++) {
			String line = new StringBuilder(lines.get(row)).reverse().toString();
			String newRow = new StringBuilder(tiltColumn(line)).reverse().toString();
			result.add(newRow);
		}
		lines = result;
	}

	public long calculateLoad() {
		int load = lines.size();
		long totalLoad = 0;
		for (int row = 0; row < lines.size() ; row++) {
			int numberOfO = (int) lines.get(row).chars().filter(c -> c == 'O').count();
			totalLoad += ((long)numberOfO * load);
			load--;
		}
		return totalLoad;
	}

	public String tiltColumn(String column) {
		StringBuilder result = new StringBuilder();
		int currIdx = 0;
		while (column.substring(currIdx).contains("#")) {
			int nextIdx = column.indexOf("#", currIdx);
			String substring = column.substring(currIdx, nextIdx);
			int numberOfO = (int) substring.chars().filter(c -> c == 'O').count();
			result.append("O".repeat(numberOfO)).append(".".repeat(nextIdx - currIdx - numberOfO)).append("#");
			currIdx = nextIdx + 1;
		}
		String substring = column.substring(currIdx);
		int numberOfO = (int) substring.chars().filter(c -> c == 'O').count();
		result.append("O".repeat(numberOfO)).append(".".repeat(column.length() - currIdx - numberOfO));
		return result.toString();
	}

	public String getColumn(int col) {
		return lines.stream().map(s -> s.substring(col, col + 1)).collect(Collectors.joining());
	}


	public String getColumn(List<String> temp, int col) {
		return temp.stream().map(s -> s.substring(col, col + 1)).collect(Collectors.joining());
	}

	public void print() {
		for (String line : lines) {
			System.out.println(line);
		}
	}

}
