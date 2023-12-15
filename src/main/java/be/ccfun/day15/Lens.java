package be.ccfun.day15;

import java.util.Objects;

public class Lens {
	private final String value;
	private int focalLength;

	public Lens(String data) {
		if (data.contains("=")) {
			String[] split = data.split("=");
			this.value = split[0];
			this.focalLength = Integer.parseInt(split[1]);
		} else {
			String[] split = data.split("-");
			this.value = split[0];
		}
	}

	public Lens(String value, int focalLength) {
		this.value = value;
		this.focalLength = focalLength;
	}



	public String getValue() {
		return value;
	}

	public int getHash() {
		return Hasher.calculateHash(value);
	}

	public int getFocalLength() {
		return focalLength;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Lens lens = (Lens) o;

		return Objects.equals(value, lens.value);
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public String  toString() {
		return "Lens{" +
				"value='" + value + '\'' +
				", focalLength=" + focalLength +
				'}';
	}
}
