package be.ccfun.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CamelMap {
	private Map<String, Node> nodes = new HashMap<>();

	public void readMap(List<String> map) {
		for (String line : map) {
			line = line.replace("= (", ",").replace(")","");
			String[] split = line.split(",");
			nodes.put(split[0].trim(), new Node(split[0].trim(), split[1].trim(), split[2].trim()));
		}
	}

	public int search(String instructions, String dest) {
		Node current = nodes.get("AAA");
		int steps = 0;
		while (true) {
			for (Character c : instructions.chars().mapToObj(i -> (char) i).toList()) {
				steps++;
				if (c == 'L') {
					current = nodes.get(current.left());
				} else {
					current = nodes.get(current.right());
				}
				if (current.name().equals(dest)) {
					return steps;
				}
			}
		}
	}

	public long search2(String instructions, Node current) {
		int steps = 0;
		while (true) {
			for (Character c : instructions.chars().mapToObj(i -> (char) i).toList()) {
				steps++;
				if (c == 'L') {
					current = nodes.get(current.left());
				} else {
					current = nodes.get(current.right());
				}
				if (current.name().endsWith("Z")) {
					return steps;
				}
			}
		}
	}

	public long ghostSearch(String instructions) {
		List<Node> currentNodes = nodes.values().stream().filter(n -> n.name().endsWith("A")).toList();
		int steps = 0;
		while (true) {
			for (Character c : instructions.chars().mapToObj(i -> (char) i).toList()) {
				steps++;
				List<Node> next = new ArrayList<>();
				for (Node current : currentNodes) {
					if (c == 'L') {
						next.add(nodes.get(current.left()));
					} else {
						next.add(nodes.get(current.right()));
					}
				}
				currentNodes = next;
				if (currentNodes.stream().filter(n -> n.name().endsWith("Z")).count() == currentNodes.size()) {
					return steps;
				}
			}
		}
	}


	public long ghostSearch2(String instructions) {
		List<Node> currentNodes = nodes.values().stream().filter(n -> n.name().endsWith("A")).toList();
		List<Long> steps = new ArrayList<>();
		for (Node node : currentNodes) {
			long result = search2(instructions, node);
			steps.add(result);
		}
		return lcm(steps);

	}

	private static long gcd(long x, long y) {
		return (y == 0) ? x : gcd(y, x % y);
	}

	public static long gcd(List<Long> numbers) {
		return numbers.stream().mapToLong(x -> x).reduce(0, CamelMap::gcd);
	}

	public static long lcm(List<Long> numbers) {
		return numbers.stream().mapToLong(x -> x).reduce(1, (x, y) -> x * (y / gcd(x, y)));
	}


}
