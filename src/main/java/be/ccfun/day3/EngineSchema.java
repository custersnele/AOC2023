package be.ccfun.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EngineSchema {

	private final List<EngineElement> engineElements = new ArrayList<>();


	public void findGears() {
		List<Symbol> list = engineElements.stream().filter(EngineElement::isStar).map(e -> e.getSymbol()).distinct().toList();
		long totalGearRatio = 0;
		for (Symbol possibleGear : list) {
			List<EngineElement> elements = engineElements.stream().filter(e -> possibleGear.equals(e.getSymbol())).toList();
			if (elements.size() == 2) {
				System.out.println("GEAR FOUND");
				List<Integer> list1 = elements.stream().map(EngineElement::getNumber).toList();
				long gearRatio = (long) list1.get(0) * list1.get(1);
				totalGearRatio += gearRatio;
			}
		}
		System.out.println(totalGearRatio);
	}


	public void displayElements() {
		engineElements.forEach(System.out::println);
	}

	public long sumPartNumbers() {
		return engineElements.stream().filter(EngineElement::isPart).mapToInt(EngineElement::getNumber).sum();
	}

	public void parseSchema(List<String> schema) {
		StringBuilder currentNumber = new StringBuilder();
		Symbol currentSymbol = null;
		for (int row = 0; row < schema.size(); row++) {
			for (int col = 0; col < schema.get(0).length(); col++) {
				if (Character.isDigit(schema.get(row).charAt(col))) {
					currentNumber.append(schema.get(row).charAt(col));
					Optional<Symbol> adjacentSymbol = Direction.getAdjacentSymbol(row, col, schema);
					if (adjacentSymbol.isPresent()) {
						currentSymbol = adjacentSymbol.get();
					}
				} else if (!currentNumber.isEmpty()) {
					engineElements.add(new EngineElement(Integer.parseInt(currentNumber.toString()), currentSymbol));
					currentNumber = new StringBuilder();
					currentSymbol = null;
				}
			}
			if (!currentNumber.isEmpty()) {
				engineElements.add(new EngineElement(Integer.parseInt(currentNumber.toString()), currentSymbol));
				currentNumber = new StringBuilder();
				currentSymbol = null;
			}
		}

	}



}
