package de.mic.day4;

import lombok.Data;

@Data
public class Order {

	private Integer sum;
	private Integer from;
	private Integer to;

	public Order(Integer sum, Integer from, Integer to) {
		this.sum = sum;
		this.from = from;
		this.to = to;
	}

}
