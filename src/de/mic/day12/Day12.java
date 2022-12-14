package de.mic.day12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import de.mic.framework.Solver;
import de.mic.framework.Vertex;

public class Day12 extends Solver {
	public static void main(String[] args) {
//		new Day12().solve("day12-test.txt").print();
		new Day12().solve("day12.txt").print();
	}

	@Override
	protected String solve() {
		List<String> rows = this.file.rows();
		List<Positions> positions = initFleld(rows);

		Positions start = findStart(positions, 'S');
		Positions end = findStart(positions, 'E');
		start.c = 'a';
		end.c = 'z';
		Vertex<Positions> s1 = new Vertex<>(start);

		initBfs(positions);

		Integer result = Integer.MAX_VALUE;
		Integer count = findWay(start, end, positions, 0, new ArrayList<>(), result);
		return "" + count;
	}

	private void initBfs(List<Positions> positions) {

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

			return a && y == p.y || b && x == p.x;
		}).collect(Collectors.toList());

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
