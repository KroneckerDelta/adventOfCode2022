package de.mic.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import de.mic.framework.Solver;

public class Day5 extends Solver {

	public static void main(String[] args) {
		new Day5().solve("day5-test.txt");

	}

	private List<Order> order = new ArrayList<>();

	private List<Stack<Character>> stack = new ArrayList<>();

	@Override
	protected String solve() {
		for (int i = 0; i < 10; i++) {
			stack.add(new Stack<Character>());
		}

		this.file.rows().stream().forEach(row -> parse(row));

		return "" + this.order.size() + " stack 1: " + stack.get(2).size();
	}

	private Object parse(String row) {
		if (row.isBlank()) {
			System.out.println("Freie Reihe!");

		} else if (row.startsWith("move")) {
			String[] split = row.split("move");
			String sum = split[1].trim().split(" ")[0];
			String from = row.split("from")[1].trim().split(" ")[0];

			String to = row.split(" ")[5];
			Order createOrder = createOrder(sum, from, to);
			this.order.add(createOrder);

		} else {
			int stack = 1;
			for (int i = 0; i < row.toCharArray().length; i += 3) {

				char bOpen = row.toCharArray()[i];
				char input = row.toCharArray()[i + 1];
				char bclose = row.toCharArray()[i + 2];

				if (bOpen == '[' && bclose == ']') {
					createStack(stack, input);
				}
				stack++;
				i++;
			}

			System.out.println("row: " + row);
		}

		return null;
	}

	private void createStack(int stack, char input) {
		System.out.println("Stack: " + stack + " input " + input);
		if (this.stack.get(stack) == null) {
			Stack<Character> s = new Stack<>();
			this.stack.add(s);
		}
		this.stack.get(stack).push(input);
	}

	private Order createOrder(String sum, String from, String to) {
		Integer s1 = Integer.valueOf(sum);
		Integer f1 = Integer.valueOf(from);
		Integer t1 = Integer.valueOf(to);
		return new Order(s1, f1, t1);

	}

}
