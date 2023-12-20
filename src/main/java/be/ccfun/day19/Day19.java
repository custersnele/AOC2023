package be.ccfun.day19;


import be.ccfun.day18.CubicMeter;
import be.ccfun.day18.Direction;
import be.ccfun.day18.Polygon;
import be.ccfun.day18.Position;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19 {
	public static void main(String[] arg) throws IOException {

		List<String> lines = Files.readAllLines(Path.of("src/main/resources/store/19"));
		Map<String, Long> ratings = new HashMap<>();
		ratings.put("x", 0L);
		ratings.put("m", 0L);
		ratings.put("a", 0L);
		ratings.put("s", 0L);
		Map<String, Workflow> workflows = new HashMap<>();
		boolean readingWorkflows = true;
		for (String line : lines) {
			if (readingWorkflows) {
				if (line.isEmpty()) {
					readingWorkflows = false;
				} else {
					Workflow workflow = new Workflow(line);
					workflows.put(workflow.getName(), workflow);
				}
			} else {
				Map<String, Integer> variables = new HashMap<>();
				// {x=787,m=2655,a=1222,s=2876}
				String[] split = line.replace("{", "").replace("}", "").split(",");
				for (String part: split) {
					String[] split1 = part.split("=");
					variables.put(split1[0], Integer.parseInt(split1[1]));
				}
				boolean accept = execute(workflows, variables);
				if (accept) {
					ratings.put("x", ratings.get("x") + variables.get("x"));
					ratings.put("m", ratings.get("m") + variables.get("m"));
					ratings.put("a", ratings.get("a") + variables.get("a"));
					ratings.put("s", ratings.get("s") + variables.get("s"));
				}
			}
		}

		System.out.println("TOTAL:" + ratings.values().stream().mapToLong(l -> l).sum());

	}

	private static boolean execute(Map<String, Workflow> workflows, Map<String, Integer> variables) {
		Workflow workflow = workflows.get("in");
		String outcome = workflow.execute(variables);
		while (!Arrays.asList("R", "A").contains(outcome)) {
			workflow = workflows.get(outcome);
			outcome = workflow.execute(variables);
		}
		return outcome.equals("A");
	}

}
