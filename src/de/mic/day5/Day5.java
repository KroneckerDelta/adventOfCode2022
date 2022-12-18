package de.mic.day5;

import java.util.ArrayList;
import java.util.List;

import de.mic.framework.Solver;

public class Day5 extends Solver {

	public static void main(String[] args) {
		new Day5().solve("day5-test.txt");
		new Day5().solve("day5.txt");

	}

	private List<Order> order = new ArrayList<>();

	private List<List<Character>> stacks = new ArrayList<>();

	@Override
	protected String solve() {
		for (int i = 0; i < 10; i++) {
			stacks.add(new ArrayList<Character>());
		}

		this.file.rows().stream().forEach(row -> parse(row));

		for (int i = 0; i < 10; i++) {
			List<Character> stack = stacks.get(i);
//			Collections.reverse(stack);
		}

		runOrder();
		String result = "";
		for (int i = 0; i < 10; i++) {
			if (!this.stacks.get(i).isEmpty()) {
				Character pop = this.stacks.get(i).get(0);
				result += pop.charValue();
			}
		}
		return result;
	}

	private void runOrder() {
		for (Order order : order) {
			System.out.println("Before Order " + order);
			List<Character> from = this.stacks.get(order.getFrom());
			List<Character> to = this.stacks.get(order.getTo());

			System.out.println("from" + from);
			System.out.println("to" + to);
			List<Character> tmp = new ArrayList<>();
			for (int i = 0; i < order.getSum(); i++) {

				Character pop = from.remove(0);
//				from.remove(pop);
				System.out.println("[" + pop + "]");
				tmp.add(0, pop);
			}
			for (Character pop : tmp) {
				to.add(0, pop);

			}
			System.out.println("After");
			System.out.println("from" + from);
			System.out.println("to" + to);
		}

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
		this.stacks.get(stack).add(input);
	}

	private Order createOrder(String sum, String from, String to) {
		Integer s1 = Integer.valueOf(sum);
		Integer f1 = Integer.valueOf(from);
		Integer t1 = Integer.valueOf(to);
		return new Order(s1, f1, t1);

	}

}
