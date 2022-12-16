package de.mic.day12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import de.mic.framework.BreadthFirstSearch;
import de.mic.framework.Solver;
import de.mic.framework.Vertex;

public class Day12 extends Solver {
	public static void main(String[] args) {
		new Day12().solve("day12-test.txt").print();
//		new Day12().solve("day12.txt").print();
	}

	@Override
	protected String solve() {
		List<String> rows = this.file.rows();
		List<Positions> positions = initFleld(rows);

		final Positions start = findStart(positions, 'S');
		final Positions end = findStart(positions, 'E');
		start.c = 'a';
		end.c = 'z';

		Vertex<Positions> startV = new Vertex<>(start);
		List<Vertex<Positions>> initBfs = initBfs(positions);

		List<Vertex<Positions>> mybeStart = initBfs.stream().filter(p -> start.equals(p.getData()))
				.collect(Collectors.toList());//
		if (mybeStart.size() > 1 || mybeStart.size() < 1) {
			throw new RuntimeException("Sollte nur 1 Start sein");
		}
		BreadthFirstSearch<Positions> bfs = new BreadthFirstSearch<>(mybeStart.get(0));

		List<Vertex<Positions>> endeWrap = initBfs.stream().filter(p -> end.equals(p.getData()))
				.collect(Collectors.toList());//

		return "" + bfs.traverse(endeWrap.get(0));
	}

	private List<Vertex<Positions>> initBfs(final List<Positions> positions) {

		List<Vertex<Positions>> result = new ArrayList<>();
		for (Positions pos : positions) {
			Vertex<Positions> vertex = new Vertex<>(pos);
			result.add(vertex);
		}
		result.stream().forEach(vertex -> {

			List<Vertex<Positions>> neighbours = getNeighbours(vertex, result);//
//			System.out.println(String.format("for %s folling neighboars found %s", pos, neighbours));
			vertex.setNeighbours(neighbours);
		});

		return result;
	}

	private Integer findWay(Positions start, Positions end, List<Positions> positions, int count,
			final List<Positions> visited, Integer result) {

//		System.out.println("positions: " + positions.size());
		count++;
		visited.add(start);
//		System.out.println("Visitied: " + visited.size());
		if (start == end) {
			System.out.println("Count: " + count + " size  " + visited.size());
			return count;
		} else {
			List<Positions> neigh = getNeighbours(start, positions);
			List<Positions> filterNeight = neigh.stream()//
					.filter(p -> fitHeight(p, start)).//
					filter(p -> notvisited(p, visited))//
					.collect(Collectors.toList());

			Iterator<Positions> iterator = filterNeight.iterator();
			while (iterator.hasNext()) {
				Positions n = iterator.next();

				Integer findWay = findWay(n, end, positions, count, visited, result);
				if (findWay < result) {
					result = findWay;
				}
				visited.remove(n);
			}

			return result;
		}

	}

	private Boolean notvisited(Positions p, List<Positions> visited) {
		return !visited.contains(p);
	}

	private Boolean fitHeight(Positions p, Positions start) {
		return (p.c - start.c) == 1 || (p.c - start.c) == 0;
	}

	private List<Positions> getNeighbours(Positions start, List<Positions> positions) {

		int x = start.x;
		int y = start.y;
		return positions.stream().filter(p -> {

			boolean a = Math.abs(p.x - x) <= 1;
			boolean b = Math.abs(p.y - y) <= 1;
			boolean me = x == p.x && y == p.y;
			return !me && (a && y == p.y || b && x == p.x);
		}).filter(p -> fitHeight(p, start)).collect(Collectors.toList());

	}

	private List<Vertex<Positions>> getNeighbours(Vertex<Positions> s, List<Vertex<Positions>> posWraps) {
		final Positions start = s.getData();
		int x = start.x;
		int y = start.y;
		return posWraps.stream().filter(pWrap -> {
			Positions p = pWrap.getData();
			boolean a = Math.abs(p.x - x) <= 1;
			boolean b = Math.abs(p.y - y) <= 1;
			boolean me = x == p.x && y == p.y;
			return !me && (a && y == p.y || b && x == p.x);
		}).filter(pp -> fitHeight(pp.getData(), start)).collect(Collectors.toList());
		// .filter(p -> fitHeight(p, start)).collect(Collectors.toList());

	}

	private Positions findStart(List<Positions> positions, char s) {
		List<Positions> collect = positions.stream().filter(p -> p.c == s).collect(Collectors.toList());
		return collect.get(0);
	}

	private List<Positions> initFleld(List<String> rows) {
		int y = 0;
		List<Positions> positions = new ArrayList<>();
		for (String row : rows) {
			positions.addAll(buildPositions(y, row));
			y++;
		}
		return positions;
	}

	private List<Positions> buildPositions(int y, String row) {

		int x = 0;
		List<Positions> posi = new ArrayList<>();
		for (char c : row.toCharArray()) {
			posi.add(new Positions(x, y, c));
			x++;
		}
		return posi;
	}
}
