package de.mic.day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import de.mic.framework.Solver;

public class Day11 extends Solver {

	public static void main(String[] args) {

		new Day11().solve("Day11-test.txt").print();
//		new Day11().solve("Day11.txt").print();
	}

	@Override
	protected String solve() {
		BigInteger result = new BigInteger("0");

		List<String> rows = this.file.rows();
		Iterator<String> iterator = rows.iterator();

		List<Monkey> monkeys = new ArrayList<>();
		while (iterator.hasNext()) {

			buildMonkey(iterator, monkeys, 3);
		}

//			System.out.println("Monkey: " + monkeys.get(0));
//			System.out.println("Monkey: " + monkeys.get(1));
//			System.out.println("Monkey: " + monkeys.get(2));
//			System.out.println("Monkey: " + monkeys.get(3));

		Round round = new Round(monkeys);
		for (int i = 0; i < 10000; i++) {
			round.next();
			System.out.println("Round: " + i);
		}
		List<BigInteger> collect = monkeys.stream().map(m -> m.inspect).sorted((o1, o2) -> o1.compareTo(o2))
				.collect(Collectors.toList());

		if (collect.get(0).equals(new BigInteger("52166")) && collect.get(1).equals(new BigInteger("47830"))) {

			System.out.println("Gefunden! " + 3);
			System.out.println("1 " + collect.get(0));
			System.out.println("2 " + collect.get(1));
			System.out.println("3 " + collect.get(2));
			System.out.println("4 " + collect.get(3));
		}
		System.out.println("1 " + collect.get(0));
		System.out.println("2 " + collect.get(1));
		System.out.println("3 " + collect.get(2));
		System.out.println("4 " + collect.get(3));
		result = collect.get(collect.size() - 1).multiply(collect.get(collect.size() - 2));
		return "" + result.toString();
	}

	private void buildMonkey(Iterator<String> iterator, List<Monkey> monkeys, int missingFactor) {
		String id = iterator.next();
		String items = iterator.next();
		String operations = iterator.next();
		String testOperator = iterator.next();
		String ifTrue = iterator.next();
		String ifFalse = iterator.next();

		// avoid last line
		if (iterator.hasNext()) {
			iterator.next(); // blank
		}

		Monkey monkey = new Monkey(id);
		monkey.addItems(items);
		monkey.addOperation(operations);
		monkey.addTestOperator(testOperator);
		monkey.addIfTrue(ifTrue);
		monkey.addIfFalse(ifFalse);

		monkey.missingValue = 9699690;

		monkeys.add(monkey);

	}

}
