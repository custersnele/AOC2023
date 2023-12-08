package be.ccfun.day8;

import be.ccfun.day7.Hand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day8 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/8"));
		CamelMap camelMap = new CamelMap();
		camelMap.readMap(lines.subList(2, lines.size()));
		String instructions = lines.get(0);
		long steps = camelMap.ghostSearch2(instructions);
		System.out.println(steps);



	}

}
