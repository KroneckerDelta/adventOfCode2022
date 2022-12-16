package de.mic.framework;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<T> {

	public Vertex<T> startVertex;

	public BreadthFirstSearch(Vertex<T> startVertex) {
		super();
		this.startVertex = startVertex;
	}

	public int traverse(Vertex<T> searchFor) {

		Queue<Vertex<T>> queue = new LinkedList<>();
		queue.add(startVertex);
		int countSteps = 0;
		while (!queue.isEmpty()) {
			Vertex<T> current = queue.poll();
			if (!current.isVisited()) {

				if (searchFor.equals(current)) {
					System.out.println("Gefunden!!");

//					return countSteps;
					countSteps++;
				}

				current.setVisited(true);
				System.out.println("current: " + current);
				queue.addAll(current.getNeighbours());
				System.out.println();
			}
		}
		return countSteps;
	}

}
