package de.mic.day15;

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
		long start = System.currentTimeMillis();
		new Day15().solve("day15-test.txt").print();
//		new Day15().solve("day15.txt").print();
		long stop = System.currentTimeMillis() - start;
		System.out.println("Time in ms: " + stop);
	}

	@Override
	protected String solve() {

		for (int i = 0; i < 4000000; i++) {
			System.out.println("i" + i);
		}
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
		;
		int startX = Math.min(findStart(beacon), all.stream().mapToInt(s -> s.getBounds().x).min().getAsInt());
		int endX = Math.max(findMax(beacon), all.stream().mapToInt(s -> s.getBounds().x).max().getAsInt());

		int y = 11;
		int count = 0;

		count = backupPartI(all, beacon, startX, endX, y, count);

		int count2 = 0;
//		count2 = backupPartI(all, beacon, startX, endX, y, count2);

		return "intersect> : " + count + " zu contains: " + count2;
	}

	private int backupPartI(List<Shape> all, Set<Point> beacon, int startX, int endX, int y, int count2) {
		for (int x = startX; x <= endX + 1; x++) {
			Point point = new Point(x, y);
			boolean found = false;
			for (Shape rec : all) {
//				if (rec.intersects(point.x, point.y, 1, 1)) {
				if (rec.contains(point)) {
					found = true;
				}
			}

			if (!found) {
				System.out.println("not Found: " + point);
			}
		}
		return count2;
	}

	private int partI(List<Shape> all, Set<Point> beacon, int startX, int endX, int y, int count) {
		for (int x = startX; x <= endX + 1; x++) {
			Point point = new Point(x, y);
			boolean found = false;
			for (Shape rec : all) {

				if (rec.intersects(point.x, point.y, 1, 1)) {
//				if (  rec.contains(point)) {
					// is in
					if (!found) {

//						System.out.println("drin: " + point);
						count++;
						found = true;
					} else {
//						System.out.println("2x drin: " + point);
					}
				} else {
					// not in
//					System.out.println("nicht drin: " + point);

				}
			}
			for (Point p : beacon) {
				if (point.x == p.x && point.y == p.y) {
					count--;
					System.out.println("Found Beacon");
				}
			}
		}
		return count;
	}

	private int findMax(Set<Point> beacon) {
		return beacon.stream().mapToInt(b -> b.x).max().getAsInt();
	}

	private int findStart(Set<Point> beacon) {
		return beacon.stream().mapToInt(b -> b.x).min().getAsInt();
	}

	private Shape createRect(String row) {

		String[] split = row.split("=");

		Integer x1 = Integer.valueOf(split[1].split(",")[0]);
		Integer y1 = Integer.valueOf(split[2].split(":")[0]);
		Integer x2 = Integer.valueOf(split[3].split(",")[0]);
		Integer y2 = Integer.valueOf(split[4]);

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
			int y = y1 + (radius - Math.abs(i));
			allX.add(x);
			allY.add(y);
		}
		// 3 -> -3
		for (int i = (radius - 1); i > -radius; i--) {
			int x = x1 + i;
			int y = y1 - (radius - Math.abs(-i));
			allX.add(x);
			allY.add(y);
		}
		if (!allX.contains(x2)) {
			throw new RuntimeException("Beacle x not in " + x2);
		}
		if (!allY.contains(y2)) {
			throw new RuntimeException("Beacle x not in " + x2);
		}

		int[] xx = { leftx, x1, rightx, x1 };
		int[] yy = { y1, downY, y1, upY };
		int zzz = 4;
//
//		int[] xx = allX.stream().mapToInt(x -> x).toArray();
//		int[] yy = allY.stream().mapToInt(x -> x).toArray();
//		int zzz = allX.size();

		Shape theCircle = new Polygon(xx, yy, zzz);
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
