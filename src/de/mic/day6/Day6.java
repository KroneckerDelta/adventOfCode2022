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

				char a = charArray[i];
				char b = charArray[i + 1];
				char c = charArray[i + 2];
				char d = charArray[i + 3];

				backup.add(a);
				backup.add(b);
				backup.add(c);
				backup.add(d);
				mySet.add(a);
				mySet.add(b);
				mySet.add(c);
				mySet.add(d);
//				

//				if (backup.size() == 4) {
				if (backup.size() == mySet.size()) {
					return i + 4;
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
