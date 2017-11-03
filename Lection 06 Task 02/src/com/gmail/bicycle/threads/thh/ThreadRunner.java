package com.gmail.bicycle.threads.thh;

import java.math.BigInteger;

import com.gmail.bicycle.threads.drivers.TimeWatcher;

public class ThreadRunner {
	public static long execute(int numOfThreads, int[] array) {
		printInfo(numOfThreads, array);

		int startIndex = 0;
		int endIndex = numOfThreads > 1 ? array.length : array.length - 1;

		SummarizeThread summarizeThread = new SummarizeThread(numOfThreads, startIndex, endIndex, array);
		Thread thread = new Thread(summarizeThread);
		TimeWatcher timeWatcher = new TimeWatcher();
		timeWatcher.start();
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		BigInteger res = summarizeThread.getCalcResult();
		long result = timeWatcher.stop();
		System.out.println(res + " <- Total SUM");

		return result;
	}

	private static void printInfo(int numOfThreads, int[] array) {
		System.out.println();
		System.out.println("<Thread Runner report>");
		System.out.println("Num of threads: " + numOfThreads);
		System.out.println("Array size: " + array.length);
	}
}
