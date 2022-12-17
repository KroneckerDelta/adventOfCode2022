package de.mic.day4;

import de.mic.framework.Solver;

public class Day4 extends Solver {

	public static void main(String[] args) {
		new Day4().solve("day4-test.txt");
		new Day4().solve("day4.txt");
	}

	@Override
	protected String solve() {

		return "= " + this.file.rows().stream().map(input -> {
			String[] split = input.split(",");
			String[] ab = split[0].split("-");
			int a = Integer.parseInt(ab[0]);
			int b = Integer.parseInt(ab[1]);
			String[] xy = split[1].split("-");
			int x = Integer.valueOf(xy[0]);
			int y = Integer.valueOf(xy[1]);

//			List<Integer> origin = IntStream.rangeClosed(a, b).boxed().toList();
//			List<Integer> listA = IntStream.rangeClosed(a, b).boxed().toList();
//			
//			List<Integer> listX = IntStream.rangeClosed(x, y).boxed().toList();
//			listA.retainAll(listX);
//			if (origin.size() > listA.size()) {
//				return 1;
//			} else {
//				return 0;
//			}

			if (a <= x && b >= y) {
				return 1;
			} else if (x <= a && y >= b) {
				return 1;
			}
			return 0;
		}).mapToInt(i -> i).sum();

	}

}
