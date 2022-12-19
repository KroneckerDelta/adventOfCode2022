package de.mic.day6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import de.mic.framework.Solver;

// four characters that are all different.
public class Day6 extends Solver {

	public static void main(String[] args) {
		new Day6().solve("day6-test.txt");
		new Day6().solve("day6.txt");
	}

	@Override
	protected String solve() {

		List<Integer> collect = this.file.rows().stream().map(row -> {

			char[] charArray = row.toString().toCharArray();

			Set<Character> mySet = new HashSet<>();
			List<Character> backup = new ArrayList<>();
			for (int i = 1; i < charArray.length - 4; i++) {

				for (int k = 0; k < 14; k++) {
					char a = charArray[i + k];
					backup.add(a);
					mySet.add(a);

				}
//				if (backup.size() == 4) {
				if (backup.size() == mySet.size()) {
					return i + 14;
				} else {
					mySet.clear();
					backup.clear();
				}
//				}

			}
			return 0;
		}).collect(Collectors.toList());

		System.out.println(collect);
		return "" + collect.get(0);
	}

}
