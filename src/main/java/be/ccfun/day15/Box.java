package be.ccfun.day15;

import java.util.ArrayList;
import java.util.List;

public class Box {
	private List<Lens> lenses = new ArrayList<>();
	private int boxNumber;

	public Box(int boxNumber) {
		this.boxNumber = boxNumber;
	}

	public void addLens(Lens lens) {
		if (lenses.contains(lens)) {
			int idx = lenses.indexOf(lens);
			lenses.set(idx, lens);
		} else {
			lenses.add(lens);
		}
	}

	public void removeLens(Lens lens) {
		lenses.remove(lens);
	}

	public long getFocusingPower() {
		long total = 0;
		for (int elt = 0; elt < lenses.size(); elt++) {
			Lens lens = lenses.get(elt);
			total += (boxNumber + 1) * (elt + 1) * (long) lens.getFocalLength();
		}
		return total;
	}

	@Override
	public String toString() {
		return "Box{" +
				"lenses=" + lenses +
				", boxNumber=" + boxNumber +
				'}';
	}
}
