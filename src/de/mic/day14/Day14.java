package de.mic.day14;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import de.mic.framework.Solver;

public class Day14 extends Solver {

	public static void main(String[] args) {
		new Day14().solve("day14-text.txt").print();

	}

	@Override
	protected String solve() {

		List<Point> list = new ArrayList<>();
		List<String> rows = this.file.rows();
		List<String[]> pointList = rows.stream()//
				.map(r -> r.split("->"))//
				.collect(Collectors.toList());
		List<String> collect2 = pointList//
				.stream()//
				.map(r -> Arrays.asList(r))//
				.flatMap(l -> l.stream())//
//				.map(s -> s.split(","))//
//				.flatMap(l -> l.stream())//
				.collect(Collectors.toList());
		List<Point> stones = collect2.stream()//
				.map(s -> createPoint(s.split(",")))//
				.collect(Collectors.toList());

		stones.addAll(new HashSet<>(fillStones(stones)));
		printRiddle(stones);
		System.out.println("=======================");
		printRiddle(stones);

//		Stream<List<String>> map2 = map.map(r -> Arrays.asList(r));
//		Stream<Stream<String[]>> map3 = map2.map(r -> r.stream().map(s -> s.split(",")));
//		List<Stream<String[]>> collect = map3.collect(Collectors.toList());
		//
//				.collect(Collectors.toList()));

		return "0";
	}

	private void printRiddle(List<Point> stones) {

		OptionalInt minY = stones.stream().mapToInt(p -> p.y).min();
		OptionalInt maxY = stones.stream().mapToInt(p -> p.y).max();
		OptionalInt minX = stones.stream().mapToInt(p -> p.x).min();
		OptionalInt maxX = stones.stream().mapToInt(p -> p.x).max();

		for (int i = 0; i <= maxY.getAsInt(); i++) {
			final int ii = i;
			List<Point> toPrint = stones.stream().filter(p -> p.y == ii).collect(Collectors.toList());
			for (int x = minX.getAsInt(); x <= maxX.getAsInt(); x++) {
				Point toDraw = null;
				for (Point point : toPrint) {
					if (point.x == x) {
						toDraw = point;
					}
				}
				if (toDraw == null) {
					System.out.print("_");
				} else {
					System.out.print("#");
				}
				toDraw = null;
			}
			System.out.println();
		}
	}

	private List<Point> fillStones(List<Point> stones) {

		List<Point> newPoints = new ArrayList<>();
		for (int i = 0; i < stones.size() - 1; i++) {

			Point from = stones.get(i);
			Point to = stones.get(i + 1);
			newPoints.addAll(createFillStones(from, to));
		}
		return newPoints;
	}

//	private void me() {
//		 Arrays.asList(r).//
//			stream()//
//			.map(p -> p.split(p))//
//			.map(p -> createPoint(p))	// TODO Auto-generated method stub
//
//	}

	private List<Point> createFillStones(Point from, Point to) {

		List<Point> newPoints = new ArrayList<>();
		if (from.x == to.x) {
			for (int y = Math.min(from.y, to.y) + 1; y < Math.max(from.y, to.y); y++) {
				newPoints.add(new Point(to.x, y));
			}
		} else if (from.y == to.y) {

			for (int x = Math.min(from.x, to.x) + 1; x < Math.max(from.x, to.x); x++) {
				newPoints.add(new Point(x, to.y));
			}
		}

		return newPoints;

	}

	private Point createPoint(String[] p) {
		return new Point(Integer.valueOf(p[0].strip()), Integer.valueOf(p[1].strip()));
	}

}
