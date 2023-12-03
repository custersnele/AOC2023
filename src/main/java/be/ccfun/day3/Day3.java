package be.ccfun.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3 {
	public static void main(String[] args) throws IOException {
		List<String> schema = Files.readAllLines(Path.of("src/main/resources/store/3"));
		EngineSchema engineSchema = new EngineSchema();
		engineSchema.parseSchema(schema);
		engineSchema.displayElements();
		System.out.println(engineSchema.sumPartNumbers());
		engineSchema.findGears();

	}

}
