package de.mic.day2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import de.mic.framework.Solver;

public class Day2 extends Solver {

	public static void main(String[] args) {
		new Day2().solve("day2-test.txt").print();
		new Day2().solve("day2.txt").print();
	}

	@Override
	protected String solve() {

		Player one = new Player();
		Player two = new Player();
		Stream<String[]> game = this.file.rowsBlankSplitted();
		game.forEach(move -> calculate(move, one, two));
		System.out.println("one: " + one.points);
		System.out.println("two: " + two.points);
		return "" + Math.max(one.points, two.points);
	}

	private void calculate(String[] move, Player one, Player two) {
		// 1 for Rock, 2 for Paper, and 3 for Scissors
		// 0 if you lost, 3 if the round was a draw, and 6 if you won
		List<String> moveAsList = Arrays.asList(move);
		one.addMove(move[0], move[1], moveAsList);
		two.addMove(move[1], move[0], moveAsList);

	}
}
