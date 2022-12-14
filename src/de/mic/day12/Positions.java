package de.mic.day12;

public class Positions {

	public int x;
	public int y;
	public char c;

	public Positions(int x, int y, char c) {
		this.x = x;
		this.y = y;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Positions [x=" + x + ", y=" + y + ", c=" + c + "]";
	}

}
