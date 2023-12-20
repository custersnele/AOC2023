package be.ccfun.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Workflow {
	private String name;
	private List<Test> tests = new ArrayList<>();

	public Workflow(List<Test> tests) {
		this.tests = tests;
	}

	public Workflow(String workflow) {
		// px{a<2006:qkq,m>2090:A,rfg}
		String[] split = workflow.split("\\{");
		name = split[0];
		String[] tests = split[1].replace('}', ' ').split(",");
		for (String test: tests) {
			this.tests.add(new Test(test.trim()));
		}
	}

	public String getName() {
		return name;
	}

	public String execute(Map<String, Integer> variables) {
		for (Test test : tests) {
			String outcome = test.execute(variables);
			if (!outcome.isEmpty()) {
				return outcome;
			}
		}
		return "";
	}
}
