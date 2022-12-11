package de.mic.day2;

import java.util.Arrays;
import java.util.List;

public class Player {

	public int points = 0;;
	String me;
	String other;

	List<String> rock = Arrays.asList("A", "X");
	List<String> paper = Arrays.asList("B", "Y");
	List<String> scissor = Arrays.asList("C", "Z");

// X for Rock, Y for Paper, and Z for Scissors
	// A for Rock, B for Paper, and C for Scissors
	// 1 for Rock, 2 for Paper, and 3 for Scissors
	public void addMove(String me, String other, List<String> move) {

		this.me = me;
		this.other = other;
		int point = 0;
		if ("AX".contains(me)) {
			point = 1;
		} else if ("BY".contains(me)) {
			point = 2;

		} else if ("CX".contains(me)) {
			point = 3;

		}
		this.points += point;
		addWinnerPoints(move);
	}

	public void addWinnerPoints(List<String> move) {
		int point = 0;
		if (isEqual(move)) {
			point = 3;

		} else if (hasWon()) {
			point = 6;
		}
		this.points += point;
	}

	private boolean hasWon() {
		if (rock.contains(me)) {
			if (paper.contains(other)) {
				return false;
			} else {
				return true;
			}
		} else if (paper.contains(me)) {

			if (rock.contains(other)) {
				return true;
			} else {
				return false;
			}
		} else if (scissor.contains(me)) {
			if (rock.contains(other)) {
				return false;
			} else {
				return true;
			}

		}

		return false;
	}

	public boolean isEqual(List<String> move) {
		return //
		move.containsAll(scissor) //
				|| move.containsAll(paper) //
				|| move.containsAll(rock);
	}
}
