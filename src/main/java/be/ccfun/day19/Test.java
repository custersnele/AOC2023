package be.ccfun.day19;

import java.util.Map;

public class Test {

	private String var;
	private Operator operator;
	private int value;
	private String outcome;

	public Test(String var, Operator operator, int value, String outcome) {
		this.var = var;
		this.operator = operator;
		this.value = value;
		this.outcome = outcome;
	}

	public Test(String test) {
		// a<2006:qkq
		if (test.contains(":")) {
			String[] split = test.split(":");
			if (split[0].contains("<")) {
				String[] split1 = split[0].split("<");
				this.var = split1[0];
				operator = Operator.LESS_THAN;
				this.value = Integer.parseInt(split1[1]);
				outcome = split[1];
			} else {
				String[] split1 = split[0].split(">");
				this.var = split1[0];
				operator = Operator.GREATER_THAN;
				this.value = Integer.parseInt(split1[1]);
				outcome = split[1];
			}
		} else {
			outcome = test;
		}
	}

	public String execute(Map<String, Integer> variables) {
		if (operator == null) {
			return outcome;
		}
		Integer valueToTest = variables.get(var);
		return switch (operator) {
			case LESS_THAN -> valueToTest < value ? outcome : "";
			case GREATER_THAN -> valueToTest > value ? outcome: "";
		};
	}

	public Map<String,Range> updateVariables(Map<String, Range> variables) {
		if (operator == null) {
			return variables;
		}

	}

}
