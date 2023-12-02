package be.ccfun.day2;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<CubeSet> cubeSets = new ArrayList<>();

	public void addCubeSet(CubeSet cubeSet) {
		cubeSets.add(cubeSet);
	}

	public CubeSet getMinCubeSet() {
		int maxGreen = cubeSets.stream().mapToInt(CubeSet::getGreen).max().getAsInt();
		int maxRed = cubeSets.stream().mapToInt(CubeSet::getRed).max().getAsInt();
		int maxBlue = cubeSets.stream().mapToInt(CubeSet::getBlue).max().getAsInt();
		return new CubeSet(maxRed, maxGreen, maxBlue);
	}
}
