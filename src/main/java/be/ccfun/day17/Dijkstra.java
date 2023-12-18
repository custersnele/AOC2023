package be.ccfun.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public long dijkstra(List<Node> nodes, Node source, Node destination ) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        List<String> visited = new ArrayList<>();

        // Add source node to the priority queue
        Node start = new Node(source.getIdx(), source.getRow(), source.getColumn(), source.getCost());
        start.setStepCost(0);
        priorityQueue.add(start);

 
        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();

            if (visited.contains(currentNode.getVisitedTag())) {
                continue;
            }

            if (currentNode.getIdx() == destination.getIdx()) {
                System.out.println("SOLUTION");
                System.out.println(currentNode.getSteps());
                System.out.println(currentNode.getStepCost());
                return currentNode.getStepCost();
            }

            visited.add(currentNode.getVisitedTag());
            System.out.println("CURRENT " + currentNode.getRow() + " " + currentNode.getColumn() + " " + currentNode.getStepCost());

            visitNeighbours(currentNode, nodes, priorityQueue);
        }
        return -1;
    }

        private void visitNeighbours(Node currentNode, List<Node> nodes, PriorityQueue<Node> priorityQueue)  {
            // process all neighbouring nodes of u
           // System.out.println("Neighbours");
            List<Node> neighbours = getNeighbours(nodes, currentNode);
            for (int i = 0; i < neighbours.size(); i++) {
                Node neighbour = neighbours.get(i);
              //  System.out.println(neighbour.getRow() + " " + neighbour.getColumn() + " " + neighbour.getCost() + " " + neighbour.getStepCost());
                priorityQueue.add(neighbour);
            }
        }

        private List<Node> getNeighbours(List<Node> nodes, Node currentNode) {
        List<Node> neighbours = new ArrayList<>();
        if (currentNode.isValidStep(Step.UP)) {
            List<Step> steps = new ArrayList<>(currentNode.getSteps());
            steps.add(Step.UP);
            addNeighbour(currentNode.getRow() - 1, currentNode.getColumn(), neighbours, nodes, steps, currentNode);
        }
        if (currentNode.isValidStep(Step.DOWN)) {
            List<Step> steps = new ArrayList<>(currentNode.getSteps());
            steps.add(Step.DOWN);
            addNeighbour(currentNode.getRow() + 1, currentNode.getColumn(), neighbours, nodes, steps, currentNode);
        }
        if (currentNode.isValidStep(Step.LEFT)) {
            List<Step> steps = new ArrayList<>(currentNode.getSteps());
            steps.add(Step.LEFT);
            addNeighbour(currentNode.getRow(), currentNode.getColumn() - 1, neighbours, nodes, steps, currentNode);
        }
        if (currentNode.isValidStep(Step.RIGHT)) {
            List<Step> steps = new ArrayList<>(currentNode.getSteps());
            steps.add(Step.RIGHT);
            addNeighbour(currentNode.getRow(), currentNode.getColumn() + 1, neighbours, nodes, steps, currentNode);
        }
        return neighbours;
    }

    private void addNeighbour(int row, int column, List<Node> neighbours, List<Node> all, List<Step> steps, Node currentNode) {
        Node node = getNode(row, column, all);
        if (node != null) {
            if (currentNode.getVisited().contains(node.getIdx())) {
                return;
            }
            Node e = new Node(node.getIdx(), node.getRow(), node.getColumn(), node.getCost());
            if (currentNode.getStepCost() + node.getCost() < node.getStepCost()) {
                e.setStepCost(currentNode.getStepCost() + node.getCost());
                e.setSteps(steps);
                e.setVisited(new ArrayList<>(currentNode.getVisited()));
                e.addVisited(currentNode.getIdx());
                e.setVisitTag(currentNode.getIdx()+"-" + steps.get(steps.size() - 1));
            }
            neighbours.add(e);
        }
    }

    private Node getNode(int row, int col, List<Node> nodes ) {
        return nodes.stream().filter(node -> node.getRow() == row && node.getColumn() == col).findFirst().orElse(null);
    }
}
 

