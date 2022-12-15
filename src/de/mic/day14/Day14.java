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
//		new Day14().solve("day14.txt").print();

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

		printRiddle(stones);
		stones.addAll(new HashSet<>(fillStones(stones)));
		stones.add(new Point(500, 3));
		stones.add(new Point(499, 3));
		stones.add(new Point(498, 2));
		stones.add(new Point(498, 3));
//		printRiddle(stones);
		System.out.println("=======================");
		printRiddle(stones);

		List<Point> before = new ArrayList<>(stones);

		for (int i = 0; i < 30; i++) {
//		while (true) {

			try {

				fillSand(stones, new Point(500, 0));
			} catch (Exception e) {
				break;
			}
//		}
		}

//		Stream<List<String>> map2 = map.map(r -> Arrays.asList(r));
//		Stream<Stream<String[]>> map3 = map2.map(r -> r.stream().map(s -> s.split(",")));
//		List<Stream<String[]>> collect = map3.collect(Collectors.toList());
		//
//				.collex	ct(Collectors.toList()));
		stones.removeAll(before);
		System.out.println("== init ==");
		printRiddle(stones);
		System.out.println("== before==");
		printRiddle(before);
		return "" + stones.size();
	}

	private boolean fillSand(List<Point> stones, Point point) {

		int minY = 0;
		int maxY = stones.stream().mapToInt(p -> p.y).max().getAsInt();
		int minX = stones.stream().mapToInt(p -> p.x).min().getAsInt();
		int maxX = stones.stream().mapToInt(p -> p.x).max().getAsInt();

		if (point.x <= minX || point.x >= maxX || point.y <= minY - 1 || point.y >= maxY) {
			return true;
		}

		// prüfe nächsten platz
		// wenn frei gehe einen Tiefer
		// wenn besetzt, prüfe links und links unten
		// wenn frei gehe auf freien Platz.
		Point checker = new Point(point.x, point.y + 1);
		printRiddle(stones, point);

		boolean isFull = contains(stones, checker);
		if (!isFull) {
			if (fillSand(stones, checker)) {
//				System.out.println("Size: " + stones.size());
			} else {

				if ((point.x <= minX || point.x >= maxX || point.y <= minY - 1 || point.y >= maxY)) {
					System.out.println("STop!");
					throw new RuntimeException();
				} else {

					stones.add(point);
//					System.out.println("Nein: " + checker);
				}

			}
		} else {
//			System.out.println("Recht lInkss");
			// prüfe links und unten

			final Point checkerLeft = new Point(point.x - 1, point.y);
			boolean anyMatchLeft = false; // contains(stones, checkerLeft);
			final Point checkerLeftDown = new Point(checkerLeft.x, checkerLeft.y + 1);
			boolean anyMatchLeftDown = contains(stones, checkerLeftDown);

			if (!anyMatchLeft && !anyMatchLeftDown) {
				return fillSand(stones, checkerLeftDown);
			}
			// prüfe rechts und unten
			final Point checkerRight = new Point(point.x + 1, point.y);
			boolean anyMatchRight = false;// contains(stones, checkerRight);
			final Point checkerRihgtDown = new Point(checkerRight.x, checkerLeft.y + 1);
			boolean anyMatchRightDown = contains(stones, checkerRihgtDown);

			if (!anyMatchRight && !anyMatchRightDown) {
				return fillSand(stones, checkerRihgtDown);
			}

			// bleibe stehen.

			// check left and right deeper
			stones.add(point);
			return true;
		}
		return true;

	}

	private boolean contains(List<Point> stones, final Point checkerLeft) {
		return stones.stream().anyMatch(p -> p.equals(checkerLeft));
	}

	private void printRiddle(List<Point> stones) {
		printRiddle(stones, new Point(500, 0));
	}

	private void printRiddle(List<Point> stones, Point trace) {

		ArrayList<Point> toPrint2 = new ArrayList<>(stones);
		System.out.println("================");

		OptionalInt minY = toPrint2.stream().mapToInt(p -> p.y).min();
		OptionalInt maxY = toPrint2.stream().mapToInt(p -> p.y).max();
		OptionalInt minX = toPrint2.stream().mapToInt(p -> p.x).min();
		OptionalInt maxX = toPrint2.stream().mapToInt(p -> p.x).max();
		toPrint2.add(trace);
		for (int i = 0; i <= maxY.getAsInt(); i++) {
			final int ii = i;
			List<Point> toPrint = toPrint2.stream().filter(p -> p.y == ii).collect(Collectors.toList());
			for (int x = minX.getAsInt(); x <= maxX.getAsInt(); x++) {
				Point toDraw = null;
				for (Point point : toPrint) {
					if (point.x == x) {
						toDraw = point;
					}
				}
				if (toDraw == null) {
					System.out.print(".");
				} else {
					System.out.print("#");
				}
				toDraw = null;
			}
			System.out.println();
		}
		toPrint2.remove(trace);
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
