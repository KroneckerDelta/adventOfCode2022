package de.mic.day1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import de.mic.framework.Solver;

public class Solve extends Solver {

	public static void main(String[] args) {

		new Solve().solve("day1-test.txt").print();
		new Solve().solve("day1.txt").print();
	}

	@Override
	protected String solve() {
		List<Elve> elves = new ArrayList<>();

		;
		Elve firstElve = new Elve();
		elves.add(firstElve);
		this.file.rows().stream().forEach(row -> {
			if (row.isBlank()) {
				// next elve
				elves.add(new Elve());
			} else {
				Integer calories = Integer.valueOf(row);
				Elve lastElve = elves.get(elves.size() - 1);
				lastElve.addCalories(calories);
			}
		});

		;
		Elve elve = elves.stream().max(Comparator.comparing(Elve::getCalories)).get();
		System.out.println("1: " + elve.getCalories());
		elves.remove(elve);
		Elve elve2 = elves.stream().max(Comparator.comparing(Elve::getCalories)).get();
		System.out.println("2: " + elve2.getCalories());
		elves.remove(elve2);
		Elve elve3 = elves.stream().max(Comparator.comparing(Elve::getCalories)).get();
		System.out.println("3: " + elve3.getCalories());
		elves.remove(elve3);
		Integer result = elve.getCalories() + elve2.getCalories() + elve3.getCalories();
		return "" + result;
	}

}
