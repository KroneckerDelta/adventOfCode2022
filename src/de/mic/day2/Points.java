package de.mic.day2;

public class Points {

	int points = 0;

	public void plus(int value) {
		this.points += value;
	}

	@Override
	public String toString() {
		return "" + points;
	}
}
