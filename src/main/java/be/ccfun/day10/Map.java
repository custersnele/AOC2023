package be.ccfun.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Map {

	private List<String> map;
	private long[][] distances;

	public Map(List<String> map) {
		this.map = map;
		distances = new long[map.size()][map.get(0).length()];
		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances[0].length; j++) {
				distances[i][j] = -1;
			}
		}
	}

	public Optional<Pipe> getPipe(Position position) {
		if (position.row() < 0 || position.row() >= map.size()) {
			return Optional.empty();
		}
		if (position.col() < 0 || position.col() >= map.get(0).length()) {
			return Optional.empty();
		}
		String symbol = map.get(position.row()).substring(position.col(), position.col() + 1);
		return Pipe.bySymbol(symbol);
	}

	public boolean isConnected(Position position, Direction direction) {
		Position next = Direction.getDirection(position, direction);
		Optional<Pipe> pipe = getPipe(next);
		if (pipe.isEmpty()) {
			return false;
		}
		return Pipe.connects(getPipe(position).get(), direction, pipe.get());
	}

	public void calculateDistances() {
		Position s = getPosition("S");
		distances[s.row()][s.col()] = 0;
		List<Position> nextPositions = calculateDistances(s);
		while (!nextPositions.isEmpty()) {
			List<Position> newPositions = calculateDistances(nextPositions.removeFirst());
			nextPositions.addAll(newPositions);
		}
	}

	public int countEnclosedTiles() {
		int count = 0;
		List<List<Tile>> tiles = createTiles();
		for (int row = 0; row < map.size(); row++) {
			for (int col = 0; col < map.get(0).length(); col++) {
				if (distances[row][col] == -1) {
					if (tiles.get(row).get(col).isInside()) {
						count++;
					}
				}
			}
		}
		return count;
	}


	public List<List<Tile>> createTiles() {
		List<List<Tile>> result = new ArrayList<>();
		for (int row = 0; row < map.size(); row++) {
			List<Tile> tiles = new ArrayList<>();
			Tile tile = new Tile();
			for (int col = 0; col < map.get(0).length(); col++) {
				Optional<Pipe> pipe = getPipe(new Position(row, col));
				if (pipe.isEmpty() || distances[row][col] == -1) {
					tile = tile.next();
					tiles.add(tile);
				} else {
					tile = tile.next(pipe.get());
					tiles.add(tile);
				}
			}
			result.add(tiles);
		}
		return result;
	}


	public boolean isValid(Position position) {
		if (position.row() < 0 || position.row() >= map.size()) {
			return false;
		}
		return position.col() >= 0 && position.col() < map.get(0).length();
	}



	public long maxDistance() {
		long max = distances[0][0];
		for (int i = 0; i < map.size(); i++) {
			for (int j = 0; j < map.get(0).length(); j++) {
				if (distances[i][j] > max) {
					max = distances[i][j];
				}
			}
		}
		return max;
	}

	public List<Position> calculateDistances(Position p) {
		long currentDist = distances[p.row()][p.col()];
		List<Position> nextPositions = new ArrayList<>();
		for (Direction direction: Direction.values()) {
			if (isConnected(p, direction)) {
				Position next = Direction.getDirection(p, direction);
				long l = distances[next.row()][next.col()];
				if (l == -1 || l > currentDist + 1) {
					distances[next.row()][next.col()] = currentDist + 1;
					nextPositions.add(next);
				}
			}
		}
		return nextPositions;
	}

	public void print() {
		for (int i = 0; i < map.size(); i++) {
			for (int j = 0; j < map.get(0).length(); j++) {
				System.out.printf("%5d", distances[i][j]);
			}
			System.out.println();
		}
	}

	public Position getPosition(String character) {
		for (int i = 0; i < map.size(); i++) {
			if (map.get(i).contains(character)) {
				return new Position(i, map.get(i).indexOf(character));
			}
		}
		return null;
	}
}
