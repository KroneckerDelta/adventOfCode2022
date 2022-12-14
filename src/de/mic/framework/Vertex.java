package de.mic.framework;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString.Exclude;

@Data
public class Vertex<T> {

	private T data;
	boolean visited;

	public Vertex(T data) {
		super();
		this.data = data;
	}

	@Exclude
	List<Vertex<T>> neighbours = new ArrayList<>();

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public List<Vertex<T>> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<Vertex<T>> neighbours) {
		this.neighbours = neighbours;
	}

}
