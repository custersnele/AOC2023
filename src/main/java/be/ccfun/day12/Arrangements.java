package be.ccfun.day12;

import java.util.HashMap;
import java.util.Map;

public class Arrangements {
	private static Map<ConditionRecord, Long> cache = new HashMap<>();

	public static long countAllCombinations(ConditionRecord conditionRecord) {
		if (cache.containsKey(conditionRecord)) {
			System.out.println("Cache hit");
			return cache.get(conditionRecord);
		} else if (!conditionRecord.hasWildcard()) {
			if (conditionRecord.isValid()) {
				return 1;
			} else {return 0;}
		} else {
			long count = 0;
			if (conditionRecord.getFromWildcard() != null) {
				ConditionRecord fromWildcard = conditionRecord.getFromWildcard();
				count += countAllCombinations(fromWildcard);
				cache.put(fromWildcard, count);
			}
			count += countAllCombinations(conditionRecord.fillWildcard());
			return count;
		}
	}

}
