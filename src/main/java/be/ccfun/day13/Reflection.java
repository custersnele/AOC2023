package be.ccfun.day13;

public class Reflection {
	private int point;
	private Type type;

	public Reflection(int point, Type type) {
		this.point = point;
		this.type = type;
	}

	public int getPoint() {
		return point;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Reflection{" +
				"point=" + point +
				", type=" + type +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Reflection that = (Reflection) o;

		if (point != that.point) {
			return false;
		}
		return type == that.type;
	}

	@Override
	public int hashCode() {
		int result = point;
		result = 31 * result + type.hashCode();
		return result;
	}
}
