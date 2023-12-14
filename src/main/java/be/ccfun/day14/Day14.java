package be.ccfun.day14;

import be.ccfun.day13.Pattern;
import be.ccfun.day13.Reflection;
import be.ccfun.day13.Type;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day14 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/14"));
		Dish dish = new Dish(lines);
		List<Integer> solutions = new ArrayList<>();
		for (int i = 0; i < 1000000000; i++) {
			if (i % 1000 == 0) {
				System.out.println(i);
				System.out.println(dish.calculateLoad());
			}
			dish.tiltNorth();
			dish.tiltWest();
			dish.tiltSouth();
			dish.tiltEast();
			//dish.print();
			//System.out.println();
			int val = (int) dish.calculateLoad();
			if (solutions.stream().filter(s -> s ==val).count() == 10) {
				System.out.println(solutions);
				for (int t = 0; t < solutions.size(); t++) {
					System.out.println(t + ";" + solutions.get(t));
				}
				break;
			}
			solutions.add(val);
		}
		System.out.println(dish.calculateLoad());


	}
}
