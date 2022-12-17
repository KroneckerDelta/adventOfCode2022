package de.mic.framework;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BreadthFirstSearch<T> {

	public Vertex<T> startVertex;
	private List<Vertex<T>> origin;

	public BreadthFirstSearch(Vertex<T> startVertex, List<Vertex<T>> origin) {
		super();
		this.startVertex = startVertex;
		this.origin = origin;
	}

	public int traverse(Vertex<T> searchFor) {

		Queue<Vertex<T>> queue = new LinkedList<>();

		startVertex.setVisited(true);
		queue.add(startVertex);
		Map<Vertex<T>, Vertex<T>> parentMap = new HashMap<>();
		int countSteps = 0;
		while (!queue.isEmpty()) {
			Vertex<T> current = queue.poll();

			if (searchFor.equals(current)) {
				System.out.println("Gefunden!!");

				break;
			}
			for (Vertex<T> neighbour : current.getNeighbours()) {
				if (!neighbour.isVisited()) {
					neighbour.setVisited(true);
					queue.add(neighbour);
					if (parentMap.get(neighbour) != null) {
						System.out.println("Schon vorhanden!");
					}
					parentMap.put(neighbour, current);

				}
			}

		}
		long count = origin.stream().filter(p -> p.isVisited()).count();
		System.out.println(String.format("Origin Size  %s  counted %s ", origin.size(), count));

		Vertex<T> key = searchFor;
		while (parentMap.get(key) != null) {
			System.out.println(key + "->");
			key = parentMap.get(key);
			if (key.equals(startVertex)) {
				System.out.println("STart gefunden");
			}
			countSteps++;
		}
		System.out.println("Start" + startVertex);
		return countSteps;

	}

}
