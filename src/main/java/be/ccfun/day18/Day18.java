package be.ccfun.day18;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day18 {
	public static void main(String[] arg) throws IOException {
		List<CubicMeter> edge = new ArrayList<>();
		Position currentPosition = new Position(0, 0);

		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/18"));
		for (String line : lines) {
			String[] split = line.split(" ");
			Direction direction = Direction.fromString(split[0]);
			for (int i = 0; i < Integer.parseInt(split[1]); i++) {
				currentPosition = Direction.getDirection(currentPosition, direction);
				edge.add(new CubicMeter(currentPosition, split[2]));
			}
		}
		List<Position> realEdge = edgeWithoutAdjacent(edge);
		List<Position> flood = new ArrayList<>();
		System.out.println(edge.size());
		int minRow = edge.stream().mapToInt(CubicMeter::getRow).min().getAsInt();
		int maxRow = edge.stream().mapToInt(CubicMeter::getRow).max().getAsInt();
		for (int row = minRow; row <= maxRow; row++) {
			int finalRow = row;
			int minCol = edge.stream().filter(c -> c.getRow() == finalRow).mapToInt(CubicMeter::getCol).min().getAsInt();
			int maxCol = edge.stream().filter(c -> c.getRow() == finalRow).mapToInt(CubicMeter::getCol).max().getAsInt();
			for (int col = minCol; col <= maxCol; col++) {
				Position pos = new Position(row, col);
				if (!edge.contains(new CubicMeter(pos)) && isInsideShape(pos, realEdge)) {
					//System.out.println("Add " + pos.row() + " " + pos.col());
					flood.add(pos);
				}
			}
		}
		System.out.println(flood.size());
		print(edge, flood);
		System.out.println("TOTAL = " + (edge.size() + flood.size()));
	}


	private static List<Position> edgeWithoutAdjacent(List<CubicMeter> edge) {
		List<Position> realEdge = new ArrayList<>();
		int minRow = edge.stream().mapToInt(CubicMeter::getRow).min().getAsInt();
		int maxRow = edge.stream().mapToInt(CubicMeter::getRow).max().getAsInt();
		for (int row = minRow; row < maxRow; row++) {
			int finalRow = row;
			int minCol = edge.stream().filter(c -> c.getRow() == finalRow).mapToInt(CubicMeter::getCol).min().getAsInt();
			int maxCol = edge.stream().filter(c -> c.getRow() == finalRow).mapToInt(CubicMeter::getCol).max().getAsInt();
			int col = minCol;
			while (col <= maxCol) {
				if (isEdge(row, col, edge) && (isEdge(row, col + 1, edge))) {
					// find end of edge
					int length = 1;
					while (isEdge(row, col+length, edge)) {
						length++;
					}
					if (isEdge(row-1, col, edge) != isEdge(row-1, col+length -1, edge)) {
						realEdge.add(new Position(row, col));
					}
					col += length;
				} else if (isEdge(row, col, edge)) {
					realEdge.add(new Position(row, col));
					col++;
				} else {
					col++;
				}

			}
		}
		return realEdge;
	}

	private static boolean isEdge(int row, int col, List<CubicMeter> edge) {
		return edge.contains(new CubicMeter(new Position(row, col)));
	}

	public static void print(List<CubicMeter> edge, List<Position> flood) {
		int minRow = edge.stream().mapToInt(CubicMeter::getRow).min().getAsInt();
		int maxRow = edge.stream().mapToInt(CubicMeter::getRow).max().getAsInt();
		int minCol = edge.stream().mapToInt(CubicMeter::getCol).min().getAsInt();
		int maxCol = edge.stream().mapToInt(CubicMeter::getCol).max().getAsInt();
		for (int row = minRow; row <= maxRow; row++) {
			for (int col = minCol; col <= maxCol; col++) {
				if (edge.contains(new CubicMeter(new Position(row, col))) ) {
					System.out.print("#");
				} else if (flood.contains(new Position(row, col))) {
					System.out.print("~");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}

	private static boolean isInsideShape(Position pos, List<Position> edge) {
		long left = edge.stream().filter(c -> c.row() == pos.row() && c.col() < pos.col()).count();
		long right = edge.stream().filter(c -> c.row() == pos.row() && c.col() > pos.col()).count();
		return left % 2 == 1 && right % 2 == 1;
	}

}
