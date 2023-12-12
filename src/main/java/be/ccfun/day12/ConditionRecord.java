package be.ccfun.day12;

import java.util.Arrays;
import java.util.Stack;

public class ConditionRecord {
	private String condition;
	private int[] groups;

	public ConditionRecord(String condition, int[] groups) {
		this.condition = condition;
		this.groups = groups;
	}

	public boolean hasWildcard() {
		return condition.contains("?");
	}

	public ConditionRecord getFromWildcard() {
		int idx = condition.indexOf("?");
		int count = 0;
		int groupIdx = 0;
		for (int i = 0; i <= idx; i++) {
			if (condition.charAt(i) == '#') {
				count++;
			} else {
				if (count > 0) {
					if (groupIdx < groups.length && groups[groupIdx] == count) {
						groupIdx++;
					} else {
						return null;
					}
				}
				count = 0;
			}
		}
		int[] newGroups = Arrays.copyOfRange(groups, groupIdx, groups.length);
		return new ConditionRecord(condition.substring(idx + 1), newGroups);
	}

	public ConditionRecord fillWildcard() {
		int idx = condition.indexOf("?");
		return new ConditionRecord(condition.substring(0, idx) + '#' + condition.substring(idx + 1), Arrays.copyOf(groups, groups.length));
	}

	public boolean isPossible(String example) {
		int currentGroup = 0;
		int idx = 0;
		for (int i = 0; i < example.length(); i++) {
			if (example.charAt(i) == '#') {
				currentGroup++;
			} else if (example.charAt(i) == '.') {
				if (currentGroup > 0) {
					if (idx < groups.length && currentGroup == groups[idx]) {
						idx++;
					} else {
						return false;
					}
				}
				currentGroup = 0;
			} else if (example.charAt(i) == '?') {
				if (currentGroup > 0) {
					return  idx < groups.length && currentGroup <= groups[idx];
				} else {
					return true;
				}
			}
		}
		if (currentGroup > 0) {
			return idx == groups.length - 1 && currentGroup == groups[idx];
		} else {
			return idx == groups.length;
		}
	}

	public long getArrangements() {
		return Arrangements.countAllCombinations(this);
	}



	public int getArrangements2() {
		// create an empty stack (we can also use set, queue, vector, or
		// any other container)
		Stack<String> stack = new Stack<>();
		stack.push(condition);        // push the pattern into the stack
		int count = 0;
		// loop till stack is empty
		while (!stack.isEmpty()) {
			// pop a string from the stack and process it
			String current = stack.pop();

			// `index` stores position of the first occurrence of wildcard
			// pattern in `curr`
			int index;
			if ((index = current.indexOf('?')) >= 0) {
				String example1 = current.substring(0, index) + '#' + current.substring(index + 1);
				if (isPossible(example1)) {
					stack.push(example1);
				}
				String example2 = current.substring(0, index) + '.' + current.substring(index + 1);
				if (isPossible(example2)) {
					stack.push(example2);
				}
			}
			// if no wildcard pattern is found, print the string
			else {
				//System.out.println(current);
				count++;
			}
		}
		return count;
	}


	public String getCondition() {
		return condition;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ConditionRecord that = (ConditionRecord) o;

		if (!condition.equals(that.condition)) {
			return false;
		}
		return Arrays.equals(groups, that.groups);
	}

	@Override
	public int hashCode() {
		int result = condition.hashCode();
		result = 31 * result + Arrays.hashCode(groups);
		return result;
	}

	public boolean isValid() {
		return isPossible(condition);
	}
}
