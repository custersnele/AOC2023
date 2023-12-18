package be.ccfun.day17;

import be.ccfun.day16.Mirror;
import be.ccfun.day16.Tile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day17 {

	public static void main(String[] arg) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/17"));
		List<Node> nodes = new ArrayList<>();
		int idx = 0;
		for (int row = 0; row < lines.size(); row++) {
			for (int col = 0; col < lines.get(0).length(); col++) {
				Node node = new Node(idx++, row, col, Integer.parseInt(lines.get(row).substring(col, col + 1)));
				nodes.add(node);
			}
		}
		Node source = nodes.stream().filter(n -> n.getIdx()== 0).findFirst().get();
		Node destination = nodes.stream().filter(n -> n.getIdx() == nodes.size() - 1).findFirst().get();
		System.out.println(new Dijkstra().dijkstra(nodes, source, destination));
	}
}
