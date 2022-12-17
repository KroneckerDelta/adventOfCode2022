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
	@lombok.EqualsAndHashCode.Exclude
	List<Vertex<T>> neighbours = new ArrayList<>();

}
