package com.gmail.bicycle;

import java.util.Arrays;

import com.gmail.bicycle.counter.CountObjects;
import com.gmail.bicycle.counter.RandomGenerator;

public class Main {

	public static void main(String[] args) {
		Integer[] arrayInt = RandomGenerator.generateIntegerArray(10);
		System.out.println("Input arrays is: " + Arrays.toString(arrayInt));
		
		CountObjects<?> counterOne = new CountObjects<>(arrayInt);
		counterOne.printCounts();

		String[] arrayString = RandomGenerator.generateStringArray(10, 4);
		System.out.println("Input arrays is: " + Arrays.toString(arrayString));

		CountObjects<?> counterTwo = new CountObjects<>(arrayString);
		counterTwo.printCounts();
	}

}
