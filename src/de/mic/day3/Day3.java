package de.mic.day3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import de.mic.framework.Solver;

public class Day3 extends Solver {

	public static void main(String[] args) {

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
		return i;
	}

	static int getPosBig(char c) {
		int i = c - 'A' + 1 + 26;
		return i;
	}

	@Override
	protected String solve() {
		List<String> rows = this.file.rows();

		Stream<List<String>> chunks = batches(rows, 3);
		Integer sum = chunks.map(myList -> findChars(myList))//
				.map(c -> getPos(c))//
				.collect(Collectors.summingInt(Integer::intValue));

//		rows.stream().collect(Collectors.partitioningBy(pre, coll));
//		System.out.println(collect);
//		Integer sum = collect.stream().map(c -> getPos(c)).collect(Collectors.summingInt(Integer::intValue));
		return "" + sum;
	}

	private Character findChars(List<String> myList) {

		String one = myList.get(0);
		String two = myList.get(1);
		String three = myList.get(2);

		char[] charArray = one.toCharArray();
		for (char c : charArray) {
			if (two.contains(String.valueOf(c)) && three.contains(String.valueOf(c))) {
				return c;
			}

		}
		throw new RuntimeException("Some Error");
	}

	public static <T> Stream<List<T>> batches(List<T> source, int length) {
		if (length <= 0)
			throw new IllegalArgumentException("length = " + length);
		int size = source.size();
		if (size <= 0)
			return Stream.empty();
		int fullChunks = (size - 1) / length;
		return IntStream.range(0, fullChunks + 1)
				.mapToObj(n -> source.subList(n * length, n == fullChunks ? size : (n + 1) * length));
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
