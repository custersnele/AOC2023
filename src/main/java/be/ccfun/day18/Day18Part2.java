package be.ccfun.day18;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day18Part2 {
	public static void main(String[] arg) throws IOException {

		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/18"));

		Position currentPosition = new Position(0,0);
		long perimeter = 0;
		Polygon polygon = new Polygon();
		for (String line : lines) {
			String hex = line.substring(line.indexOf("(") + 1, line.length() - 2);
			String instruction = line.substring(line.length() - 2, line.length() - 1);
			long value = Long.decode(hex);
			perimeter += value;
			System.out.println(value);
			Direction direction = Direction.values()[Integer.parseInt(instruction)];
			Position next = new Position(currentPosition.row() + direction.getDeltaRow() * value,  currentPosition.col() + direction.getDeltaCol() * value);
			polygon.addVertex(next);
			currentPosition = next;
		}
		System.out.println(polygon.area());
		System.out.println("AREA " +BigDecimal.valueOf(polygon.area()).toPlainString());
		System.out.println("PERIMETER " + BigDecimal.valueOf(polygon.perimeter()).toPlainString() + " " + perimeter);
		System.out.println("PICK " + BigDecimal.valueOf(polygon.pickStheorem()).toPlainString());
	}
}
