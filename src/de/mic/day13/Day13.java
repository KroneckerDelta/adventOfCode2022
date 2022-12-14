package de.mic.day13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import de.mic.day2.Points;
import de.mic.framework.Solver;

public class Day13 extends Solver {

	public static void main(String[] args) {
//		new Day13().solve("day13-test.txt").print();
		new Day13().solve("day13.txt").print();
	}

	@Override
	protected String solve() {

		List<String> rows = this.file.rows();
		Iterator<String> iter = rows.iterator();

		Points p = new Points();
		int count = 1;

		while (iter.hasNext()) {
			String firstLine = iter.next();
			String secondLine = iter.next();
			System.out.println(firstLine);
			System.out.println(secondLine);
			boolean compareMaciejLogik = compareMaciejLogik(firstLine, secondLine);
			if (compareMaciejLogik) {
				p.plus(count);
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
//	
//			List<List<Integer>> l1 = parseToList(firstLine);
//			List<List<Integer>> l2 = parseToList(secondLine);
//			// 1, 2, 4 ,6
//			int right = compare(l1, l2);
//			if (right > 0) {
//
//				p.plus(count);
//			}
			// blank
			if (iter.hasNext()) {
				iter.next();
			}
			count++;
		}

		return p.toString();
	}

	private boolean compareMaciejLogik(String firstLine, String secondLine) {
		// delete brackets until digit
		// compare until comma
		// type missmatch add bracket
		// compare again

		int length = firstLine.length();
		int length2 = secondLine.length();
		int tmp = length > length2 ? length : length2;
		for (int i = 0; i < tmp; i++) {

			if (secondLine.isEmpty()) {
				return false;
			}
			char n1 = firstLine.charAt(0);
			char n2 = secondLine.charAt(0);

			if (twoOpenBrackets(n1, n2) || hasComma(n1, n2) || hasCloseBrackets(n1, n2)) {
				firstLine = firstLine.substring(1);
				secondLine = secondLine.substring(1);
			} else if (n1 == '[' && n2 != '[') {
				secondLine = "[".concat(secondLine);
			} else if (n2 == '[' && n1 != '[') {
				firstLine = "[".concat(firstLine);
			} else if (n1 == ']' && n2 != ']') {
				return true;
//			} else if ((n2 == ']' && n1 == ',')) {
//				firstLine = "]".concat(firstLine);

			} else if (n2 == ']' && n1 != ']') {
				if (n1 == ',') {
//					System.out.println("Wie oft ?");

					secondLine = secondLine.substring(1);
				} else
					return false;
			} else {

				String split1 = extractDigit(firstLine);
				String split2 = extractDigit(secondLine);

				Integer left = Integer.valueOf(split1.trim());
				Integer right = Integer.valueOf(split2.trim());

				if (left == right) {
					// rekursion!
					firstLine = firstLine.substring(split1.length());
					secondLine = secondLine.substring(split2.length());
				} else {
					return left < right;
				}

			}
		}
		return length2 > length;
	}

	private String extractDigit(String firstLine) {
		char n = firstLine.charAt(1);
		if (Character.isDigit(n)) {
			return firstLine.substring(0, 2);
		}

		return firstLine.substring(0, 1);
	}

	private boolean hasCloseBrackets(char n1, char n2) {
		return n1 == ']' && n2 == ']';
	}

	private boolean hasComma(char n1, char n2) {
		return n1 == ',' && n2 == ',';
	}

	private boolean twoOpenBrackets(char n1, char n2) {
		return n1 == '[' && n2 == '[';
	}

	private int compare(List<List<Integer>> l1, List<List<Integer>> l2) {
		// if l1. is emtpy, right wins
		if (l1.isEmpty()) {
			return getIndex(l2);
		}

		if (l1.size() == l2.size()) {
			for (int i = 0; i < l1.size(); i++) {
				List<Integer> list1 = l1.get(i);
				List<Integer> list2 = l2.get(i);
				if (list1.isEmpty() && !list2.isEmpty()) {
					return getIndex(l2);
				} else {

					for (int x1 = 0; x1 < list1.size(); x1++) {
						try {

							Integer left = list1.get(x1);
							Integer right = list2.get(x1);
							if (left < right) {
								return getIndex(l2);
							} else if (right < left) {
								return 0;
							}

						} catch (Exception e) {
							// right got more elememt1!
							return 0;
						}

					}
					// at this point size is important!
					if (list2.size() > list1.size()) {
						return getIndex(l2);
					}
				}

			}
		} else {

			if (l2.isEmpty()) {
				return 0;
			}

			for (int i = 0; i < l1.size(); i++) {
				List<Integer> list1 = l1.get(i);
				try {
					List<Integer> list2 = l2.get(i);

					for (int x1 = 0; x1 < list1.size(); x1++) {
						try {

							Integer left = list1.get(x1);
							Integer right = list2.get(x1);
							if (left < right) {
								return getIndex(l2);
							} else if (right < left) {
								return 0;
							}

						} catch (Exception e) {
							// right got more elememt1!
							return getIndex(l2);
						}

					}
				} catch (Exception e) {
					return 0;
				}

			}

		}

		return 0;
	}

	private int getIndex(List<List<Integer>> l2) {
		if (!l2.isEmpty()) {

		}
		return 1;
	}

	private List<List<Integer>> parseToList(String row) {

		List<List<Integer>> result = new ArrayList<>();
		Queue<List<Integer>> queue = new LinkedList<>();
		List<Integer> tmp = null;
		boolean open = false;
//		row = replace(row);
		for (int i = 0; i < row.length(); i++) {
			char charAt = row.charAt(i);
			switch (charAt) {
			case '[': {
				// KLammer auf == neue Liste!
				tmp = new ArrayList<>();
				queue.add(tmp);
				result.add(tmp);
				open = true;
				break;
			}
			case ']': {
				// List wird geschlossen!
				// zurück auf die Liste vorher
				tmp = queue.poll();
				open = false;
				break;
			}
			case ',': {

				break;
			}

			default:
				if (!open) {
					tmp = new ArrayList<>();
					result.add(tmp);
				}
				// hinzufügen der Zahl zur aktuellen Liste!
				tmp.add(Integer.valueOf(String.valueOf(charAt)));
			}
		}
//		Iterator<List<Integer>> iterator = queue.iterator();
//		while (iterator.hasNext()) {
//			result.add(iterator.next());
//
//		}
//		List<Integer> list = result.get(0);
//		if (list.isEmpty()) {
//			result.remove(list);
//		}
		return result;

	}

	private String replace(String row) {
		row = row.substring(1, row.lastIndexOf("]"));
		return row;
	}

}
