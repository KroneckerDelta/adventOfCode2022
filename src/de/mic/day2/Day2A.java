package de.mic.day2;

import java.util.stream.Stream;

import de.mic.framework.Solver;

public class Day2A extends Solver {

	public static void main(String[] args) {
		new Day2A().solve("day2-test.txt").print();
		new Day2A().solve("day2.txt").print();
	}

// X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"
	@Override
	protected String solve() {
		Stream<String[]> rowsBlankSplitted = this.file.rowsBlankSplitted();
		Points points = new Points();
		rowsBlankSplitted.forEach(move -> {
			String myMove = move[1];
			String opposite = move[0];
			if ("X".equalsIgnoreCase(myMove)) {
				points.plus(0);
				if (opposite.equalsIgnoreCase("A")) {
					points.plus(3);
				} else if (opposite.equalsIgnoreCase("C")) {
					points.plus(2);

				} else {
					points.plus(1);

				}
			} else if ("Y".equalsIgnoreCase(myMove)) {
				points.plus(3);
				if (opposite.equalsIgnoreCase("A")) {
					points.plus(1);
				} else if (opposite.equalsIgnoreCase("B")) {
					points.plus(2);

				} else {
					points.plus(3);

				}

			} else {
				points.plus(6);
				if (opposite.equalsIgnoreCase("B")) {
					points.plus(3);
				} else if (opposite.equalsIgnoreCase("C")) {
					points.plus(1);

				} else {
					points.plus(2);

				}
			}

		});

		return points.toString();
	}

}
