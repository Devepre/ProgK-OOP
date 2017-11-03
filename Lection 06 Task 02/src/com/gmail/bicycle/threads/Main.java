package com.gmail.bicycle.threads;

public class Main {

	public static void main(String[] args) {
		int numOfThreadsOne = 1;
		int numOfThreadsCompareTo = 3;
		for (int numOfValues = 5000; numOfValues < 73511495; numOfValues *= (Math.random() * 10)) {
			int res = Race.start(numOfValues, numOfThreadsOne, numOfThreadsCompareTo);
			System.out.println("\t" + res + "% efficiency estimated");

		}
		System.out.println("<End of main thread>");
	}

}
