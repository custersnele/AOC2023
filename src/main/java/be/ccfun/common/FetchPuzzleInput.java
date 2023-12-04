package be.ccfun.common;

import java.nio.file.Path;

public class FetchPuzzleInput {

	public static void main(String[] args) {
		int day = 4;
		int year = 2023;
		PuzzleInputFetcher pif = new PuzzleInputFetcher(Path.of("src/main/resources/store"),  Path.of("src/main/resources/sessiontoken"));
		pif.getPuzzleInput(year, day);

	}


}
