package de.mic.day12;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Positions other = (Positions) obj;
		return x == other.x && y == other.y;
	}

}
