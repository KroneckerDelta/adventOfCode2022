package de.mic.framework;

public abstract class Solver {

	String solution;
	String filename;
	protected FileIo file;

	public void print() {
		System.out.println("Solution: " + solution);
	}

	public Solver solve(String filename) {
		this.filename = filename;
		this.file = new FileIo(filename);
		this.solution = solve();
		print();
		return this;
	}

	protected abstract String solve();

}
