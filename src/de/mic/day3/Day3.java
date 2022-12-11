package de.mic.day3;

import java.util.List;
import java.util.stream.Collectors;

import de.mic.framework.Solver;

public class Day3 extends Solver {

	public static void main(String[] args) {

		System.out.println("" + getPosBig('A'));
		System.out.println("" + getPosBig('M'));
		System.out.println("" + getPosBig('Z'));

		new Day3().solve("Day3-test.txt").print();
		new Day3().solve("Day3.txt").print();
	}

	static int getPos(char c) {

		if (Character.isLowerCase(c)) {
			return getPosSmall(c);

		} else {
			return getPosBig(c);
		}
	}

	static int getPosSmall(char c) {
		int i = c - 'a' + 1;
		System.out.println(i);
		return i;
	}

	static int getPosBig(char c) {
		int i = c - 'A' + 1 + 26;
		System.out.println(i);
		return i;
	}

	@Override
	protected String solve() {
		List<String> rows = this.file.rows();
		List<Character> collect = rows.stream().map(m -> splitAndFindChar(m)).collect(Collectors.toList());
		System.out.println(collect);
		Integer sum = collect.stream().map(c -> getPos(c)).collect(Collectors.summingInt(Integer::intValue));
		return "" + sum;
	}

	private Character splitAndFindChar(String row) {

		String substring1 = row.substring(0, row.length() / 2);
		String substring2 = row.substring(row.length() / 2, row.length());

		char[] charArray = substring1.toCharArray();
		for (char c : charArray) {
			if (substring2.contains(String.valueOf(c))) {
				return c;
			}

		}

		throw new RuntimeException(String.format("NO Char found s1 {} s1 {}", substring1, substring2));
	}

}
