package de.mic.day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*
 * Monkey 0:
  Starting items: 79, 98
  Operation: new = old * 19
  Test: divisible by 23
    If true: throw to monkey 2
    If false: throw to monkey 3
 */
public class Monkey {

	public Integer id;
	List<Integer> items;
	Integer divisible;
	Integer ifTrue;
	Integer ifFalse;
	Integer newValue;
	boolean worry = false;
	String math;
	String operationValue;

	public BigInteger inspect = new BigInteger("0");

	public Monkey(String id) {
		String[] split = id.split(" ");
		String realId = split[1].split(":")[0];
		this.id = Integer.valueOf(realId);
	}

	public void addItems(String items) {
		String[] numbers = items.split(":")[1].split(",");
		this.items = new ArrayList<Integer>();
		for (String n : numbers) {
			Integer valueOf = Integer.valueOf(n.trim());
			this.items.add(valueOf);
		}

	}

	public void addOperation(String operations) {
		String[] splittedOperator = operations.split("=")[1].trim().split(" ");
		this.math = splittedOperator[1].trim();
		this.operationValue = splittedOperator[2].trim();

	}

	public Integer newValue(Integer oldValue) {

		Integer operatorValue;
		try {
			operatorValue = Integer.valueOf(this.operationValue);

		} catch (Exception e) {
			operatorValue = oldValue;
		}

		Integer newValue = 0;

		if (math.equals("+")) {

			newValue = oldValue + operatorValue;
		} else {
			newValue = oldValue * operatorValue;
		}

		return newValue;
	}

	public void addTestOperator(String testOperator) {
		String[] split = testOperator.trim().split(" ");
		this.divisible = extractNumber(split);

	}

	private Integer extractNumber(String[] split) {
		Integer result = 0;
		for (String maybeNummber : split) {
			try {
				Integer valueOf = Integer.valueOf(maybeNummber);
				result = valueOf;
				break;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return result;
	}

	public void addIfTrue(String ifTrue) {
		this.ifTrue = extractNumber(ifTrue.split(" "));

	}

	public void addIfFalse(String ifFalse) {
		this.ifFalse = extractNumber(ifFalse.split(" "));

	}

	public List<Integer> getItems() {
		return items;

	}

	public void addItem(Integer value) {
		this.items.add(value);
	}

	public Integer inspect(Integer item) {
		inspect = inspect.add(BigInteger.ONE);
		return newValue(item) / 3;
	}

	public Integer sendTo(Integer value) {
		if (value % divisible == 0) {
			return ifTrue;
		}
		return ifFalse;
	}

	public void remove(List<Integer> items) {
		this.items.removeAll(items);

	}

	@Override
	public String toString() {
		return "Monkey [id=" + id + ", items=" + items + ", divisible=" + divisible + ", ifTrue=" + ifTrue
				+ ", ifFalse=" + ifFalse + ", newValue=" + newValue + ", worry=" + worry + ", math=" + math
				+ ", operationValue=" + operationValue + ", inspect=" + inspect + "]";
	}

}
