package be.ccfun.day2;

import java.util.Map;

public class CubeSet {
	private int red;
	private int green;
	private int blue;

	public CubeSet(String data) {
		String[] split = data.split(",");
		for (String x : split) {
			if (x.trim().endsWith("green")) {
				green = Integer.parseInt(x.trim().split(" ")[0]);
			} else if (x.trim().endsWith("red")) {
				red = Integer.parseInt(x.trim().split(" ")[0]);
			} else {
				blue = Integer.parseInt(x.trim().split(" ")[0]);
			}
		}
	}

	public CubeSet(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public long getPower() {
		return (long) red * green * blue;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public int getRed() {
		return red;
	}

	public boolean isPossibleFrom(CubeSet otherCubeSet) {
		return green <= otherCubeSet.green && red <= otherCubeSet.red && blue <= otherCubeSet.blue;
	}
}
