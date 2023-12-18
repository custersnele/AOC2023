package be.ccfun.day17;

import be.ccfun.day16.Direction;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Node implements Comparable<Node> {
    private int idx;
    private int row;
	private int column;
    private int cost;
	private int stepCost = Integer.MAX_VALUE;
	private List<Step> steps = new ArrayList<>();
	private List<Integer> visited = new ArrayList<>();
	private String visitTag = "";

	public int getStepCost() {
		return stepCost;
	}

	// Constructor 2
    public Node(int idx, int row, int column, int cost) {
        this.idx = idx;
        this.row = row;
		this.column = column;
        this.cost = cost;
    }

	public int getMovesInSameDirection() {
		if (steps.size() < 2) {
			return 0;
		}
		Step prevStep = steps.get(steps.size() - 1);
		int moves = 0;
		int idx = steps.size() - 2;
		while (idx >= 0 &&  steps.get(idx) == prevStep) {
			moves++;
			idx--;
		}
		return moves;
	}

	public String getVisitedTag() {
		return visitTag;//row + "-" + column + "-" + getMovesInSameDirection();
	}

	public void setStepCost(int stepCost) {
		this.stepCost = stepCost;
	}

	public int getRow() {
		return row;
	}

	public void setVisitTag(String visitTag) {
		this.visitTag = visitTag + "-" + getMovesInSameDirection();
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public void setVisited(List<Integer> visited) {
		this.visited = visited;
	}

	public List<Integer> getVisited() {
		return visited;
	}

	public void addVisited(int idx) {
		visited.add(idx);
	}

	public int getColumn() {
		return column;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getIdx() {
		return idx;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Node node = (Node) o;

		return idx == node.idx;
	}

	@Override
	public int hashCode() {
		return idx;
	}

	@Override
	public int compareTo(Node o) {
		// kies het langste path met de laagste stepcost
		if (this.stepCost == o.stepCost) {
			return o.steps.size() - steps.size();
		}
		return this.stepCost - o.stepCost;
	}

	public boolean isValidStep(Step step) {
		if (steps.size() >= 3) {
			return steps.get(steps.size() - 1) != step || steps.get(steps.size() - 2) != step || steps.get(steps.size() - 3) != step;
		}
		return true;
	}
}
