package de.mic.day11;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Round {

	private List<Monkey> monkeys;

	public Round(List<Monkey> monkeys) {
		this.monkeys = monkeys;
	}

	public void next() {
		Iterator<Monkey> iterator = monkeys.stream().filter(monkey -> !monkey.items.isEmpty()).iterator();
		while (iterator.hasNext()) {
			Monkey monkey = iterator.next();
			List<Integer> items = monkey.getItems();

			for (Integer item : items) {
				Integer newValue = monkey.inspect(item);
				Integer sendTo = monkey.sendTo(newValue);
				addToMonkey(newValue, sendTo);

			}
			monkey.remove(items);

		}
		System.out.println("Monkey: " + monkeys.get(0));
		System.out.println("Monkey: " + monkeys.get(1));
		System.out.println("Monkey: " + monkeys.get(2));
		System.out.println("Monkey: " + monkeys.get(3));
	}

	private void addToMonkey(Integer newValue, Integer sendTo) {

		Monkey monkey = monkeys.stream().filter(m -> m.id == sendTo).collect(Collectors.toList()).get(0);
		monkey.addItem(newValue);
	}

}
