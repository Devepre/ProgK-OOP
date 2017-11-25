package com.gmail.bicycle.counter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountObjects<T> {
	private final T[] array;
	private final Map<?, Integer> numbers;

	public CountObjects(T[] array) {
		super();
		if (array == null) {
			throw new NullPointerException();
		}
		this.array = array;
		this.numbers = createMapCounts(this.array);
	}

	/**
	 * prints counting statistic to the default System.out
	 */
	public void printCounts() {
		System.out.println("Counting statistic: ");
		this.numbers.forEach((key, value) -> System.out.println(key + " = " + value));
		System.out.println();
	}
	
	/**
	 * @param array to count equal elements
	 * @return {@code Map} where key = element, value = count of element
	 */
	public <P> Map<T, Integer> createMapCounts(T[] array) {
		Map<T, Integer> number = new HashMap<>();

		Arrays.stream(array)
			.forEach(el -> number.put(
						el, 
						number.get(el) != null ?
							number.get(el) + 1 :
							1
					));

		return number;
	}

}
