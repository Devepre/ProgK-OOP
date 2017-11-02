package com.gmail.bicycle.threads;

public class Main {
	private static Thread[] threads;
	private static FactorialThread[] factorialThreadArray;

	public static void main(String[] args) {
		System.out.println("main method start");

		int n = 100;
		generateEverything(n);
		startThreads(threads);
		joinThreads(threads);
		showResults();

		System.out.println("End of main method");
	}

	private static void generateEverything(int n) {
		factorialThreadArray = new FactorialThread[n];
		threads = generateThreads(n);
	}

	private static Thread[] generateThreads(int n) {
		Thread[] threads = new Thread[n];
		for (int i = 0; i < n; i++) {
			FactorialThread factorialThread = new FactorialThread(i + 1);
			factorialThreadArray[i] = factorialThread;
			Thread thread = new Thread(factorialThread);
			threads[i] = thread;
		}
		return threads;
	}

	private static void startThreads(Thread... threads) {
		for (Thread thread : threads) {
			thread.start();
		}
	}

	private static void joinThreads(Thread... threads) {
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void showResults() {
		for (FactorialThread factorialThread : factorialThreadArray) {
			System.out.println(factorialThread.getNumber() + "!=" + factorialThread.getResult() + " calculated by" + factorialThread.getName());
		}
	}

}
