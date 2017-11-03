package com.gmail.bicycle.threads;

import com.gmail.bicycle.threads.drivers.ArrayHandler;
import com.gmail.bicycle.threads.thh.ThreadRunner;

public class Race {
	public static int start(int numOfValues, int numOfThreadsOne, int numOfThreadsCompareTo) {
		int[] array = ArrayHandler.generateArray(numOfValues, Integer.MAX_VALUE - 100, Integer.MAX_VALUE);

		long timeOne = ThreadRunner.execute(numOfThreadsOne, array);
		long timeTwo = ThreadRunner.execute(numOfThreadsCompareTo, array);

		if (timeOne == 0 || timeTwo == 0) {
			return -0;
		}

		double difference = 0;
		try {
			difference = (double) (timeOne - timeTwo) / timeOne * 100;
		} catch (ArithmeticException e) {
			return 0;
		}
		return (int) difference;
	}
}
