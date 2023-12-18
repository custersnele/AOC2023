package be.ccfun.day18;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
	private List<Position> vertices = new ArrayList<>();

	public void addVertex(Position position) {
		vertices.add(position);
	}

	public double perimeter() {
		double distance = 0;
		int len = vertices.size();
		for(int i = 0; i < len; i++) {
			distance += distance(vertices.get(i), vertices.get((i+1)%len));
		}
		return distance;
	}

	public static double distance(Position position1, Position position2) {
		return Math.sqrt((position2.col() - position1.col()) * (position2.col() - position1.col())
				+ (position2.row() - position1.row()) * (position2.row() - position1.row()));
	}

	public double area() {
		double sum = 0;
		int n = vertices.size();
		List<Position> antiClockwise = new ArrayList<>(vertices).reversed();
		for (int i = 0; i < n - 1; i++) {
			sum += antiClockwise.get(i).col() * antiClockwise.get(i + 1).row();
			sum -= antiClockwise.get(i).row() * antiClockwise.get(i + 1).col();
		}
		sum += antiClockwise.get(n - 1).col() * antiClockwise.get(0).row();
		sum -= antiClockwise.get(n - 1).row() * antiClockwise.get(0).col();
		return Math.abs(sum) / 2.0;
	}

	public double pickStheorem(long perimeter) {
		return area() + (perimeter / 2.0) + 1;
	}

	public double pickStheorem() {
		return area() + (perimeter() / 2.0) + 1;
	}

	public int getPoints() {
		return vertices.size();
	}
}
