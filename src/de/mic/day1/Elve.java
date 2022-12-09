package de.mic.day1;

import java.util.Objects;

public class Elve {

	private Integer calories = 0;

	public void addCalories(Integer _calories) {
		this.setCalories(this.getCalories() + _calories);

	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	@Override
	public int hashCode() {
		return Objects.hash(calories);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elve other = (Elve) obj;
		return Objects.equals(calories, other.calories);
	}

}
