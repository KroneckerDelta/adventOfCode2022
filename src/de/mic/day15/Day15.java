package de.mic.day15;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.mic.framework.Solver;

public class Day15 extends Solver {
	public static void main(String[] args) {
		new Day15().solve("day15-test.txt").print();
//		new Day15().solve("day15.txt").print();
	}

	@Override
	protected String solve() {

		List<String> rows = this.file.rows();
		Iterator<String> iterator = rows.iterator();
		List<Shape> all = new ArrayList<>();
		Set<Point> beacon = new HashSet<>();
		while (iterator.hasNext())

		{
			String value = iterator.next();
			Shape createRect = createRect(value);
			all.add(createRect);
			beacon.add(createBeacon(value));
		}

		int startX = beacon.stream().mapToInt(b -> b.x).min().getAsInt();
		int endX = beacon.stream().mapToInt(b -> b.x).max().getAsInt();
		int y = 10;
		int count = 0;
		for (int x = startX; x <= endX; x++) {
			Point point = new Point(x, y);
			for (Shape rec : all) {
				if (rec.contains(point)) {
					// is in
					count++;
					break;
				} else {
					// not in

				}
			}
			for (Point p : beacon) {
				if (point.x == p.x && point.y == p.y) {
					count--;
					System.out.println("Found Beacon");
				}
			}
		}

		return "not solved: " + count;
	}

	private Shape createRect(String row) {

		String[] split = row.split("=");

		Integer x1 = Integer.valueOf(split[1].split(",")[0]);
		Integer y1 = Integer.valueOf(split[2].split(":")[0]);
		Integer x2 = Integer.valueOf(split[3].split(",")[0]);
		Integer y2 = Integer.valueOf(split[4]);
		Dimension dimension = new Dimension(x2, y2);

		Integer radius = Math.abs(x1 - x2) + Math.abs(y1 - y2);

		System.out.println("Radius : " + radius);
		int leftx = x1 - radius;
		int rightx = x1 + radius;
		int upY = y1 - radius;
		int downY = y1 + radius;

		List<Integer> allX = new ArrayList<>();
		List<Integer> allY = new ArrayList<>();
		for (int i = -radius; i <= radius; i++) {
			int x = x1 - i;
			int y = radius + Math.abs(i);
			allX.add(x);
			allY.add(y);
		}
		for (int i = (radius - 1); i > -radius; i--) {
			int x = x1 + i;
			int y = y1 - Math.abs(radius - i);
			allX.add(x);
			allY.add(y);
		}
		System.out.println("Size x:" + allX.size());
		System.out.println("Size y:" + allY.size());
		if (!allX.contains(x2)) {
			throw new RuntimeException("Beacle x not in " + x2);
		}
		if (!allY.contains(y2)) {
			throw new RuntimeException("Beacle x not in " + x2);
		}

		int[] xx = allX.stream().mapToInt(i -> i).toArray();
		int[] yy = allY.stream().mapToInt(i -> i).toArray();
		int zzz = 4;

		Shape theCircle = new Polygon(xx, yy, allX.size());
//		Shape theCircle = new Ellipse2D.Double(x1 - radius, y1 - radius, 2.0 * radius, 2.0 * radius);

		return theCircle;
	}

	private Point createBeacon(String row) {

		String[] split = row.split("=");

		Integer x1 = Integer.valueOf(split[1].split(",")[0]);
		Integer y1 = Integer.valueOf(split[2].split(":")[0]);
		Integer x2 = Integer.valueOf(split[3].split(",")[0]);
		Integer y2 = Integer.valueOf(split[4]);

		return new Point(x2, y2);
	}
}
