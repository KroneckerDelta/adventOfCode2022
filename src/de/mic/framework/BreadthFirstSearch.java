package de.mic.framework;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<T> {

	public Vertex<T> startVertex;

	public BreadthFirstSearch(Vertex<T> startVertex) {
		super();
		this.startVertex = startVertex;
	}

	public void traverse() {

		Queue<Vertex<T>> queue = new LinkedList<>();
		queue.add(startVertex);

		while (!queue.isEmpty()) {
			Vertex<T> current = queue.poll();
			current.setVisited(true);
			System.out.println();
			queue.addAll(current.getNeighbours());
		}
	}

}
