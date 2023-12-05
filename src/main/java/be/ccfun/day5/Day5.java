package be.ccfun.day5;

import be.ccfun.day4.Card;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day5 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/5"));
		List<Long> seedRanges = Arrays.stream(lines.get(0).split(":")[1].trim().split(" ")).toList().stream().map(Long::parseLong).toList();
		//List<Long> seeds = new ArrayList<>();
		List<Map> maps = new ArrayList<>();
		Map currentMap = null;
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line.contains("map")) {
				String[] fromTo = line.split(" ")[0].split("-to-");
				currentMap = new Map(fromTo[0], fromTo[1]);
				maps.add(currentMap);
			} else if (!line.isBlank()){
				List<Long> list = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();
				currentMap.addRange(new Range(list.get(0), list.get(1), list.get(2)));
			}
		}
		long lowestLocation = Integer.MAX_VALUE;
		for (int i = 0; i < seedRanges.size(); i += 2) {
			for (long seed = seedRanges.get(i); seed < seedRanges.get(i) + seedRanges.get(i + 1); seed++) {
				long location = translateToLocation(seed, maps);
				if (location < lowestLocation) {
					lowestLocation = location;
					System.out.println(lowestLocation);
				}
			}
		}
		System.out.println(lowestLocation);


	}

	public static long translateToLocation(long seed, List<Map> maps) {
		Map map = findMap(maps, "seed");
		while (!map.getTo().equals("location")) {
			//System.out.println(map.getFrom() + " " + map.getTo() + " " + seed);
			seed = map.translate(seed);
			//System.out.println(seed);
			map = findMap(maps, map.getTo());
		}
		return map.translate(seed);

	}

	public static Map findMap(List<Map> maps, String from) {
		for (Map map : maps) {
			if (map.getFrom().equals(from)) {
				return map;
			}
		}
		return null;
	}

}
