package de.mic.day12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch {
	/**
	 * 
	 * @param nodes number of nodes
	 * @param s source vertex
	 */
	public void search(int nodes, int s) {

		List<ArrayList<Integer>> adj = new ArrayList<>(); // adjacency list representation

		LinkedList<Integer> queue = new LinkedList<Integer>();
		boolean used[] = new boolean[nodes];
		int d[] = new int[nodes];
		int p[] = new int[nodes];

		queue.push(s);
		used[s] = true;
		p[s] = -1;
		while (!queue.isEmpty()) {
			int v = queue.pop();
			for (int u : adj.get(v)) {
				if (!used[u]) {
					used[u] = true;
					queue.push(u);
					d[u] = d[v] + 1;
					p[u] = v;
				}
			}
		}

	}
}
