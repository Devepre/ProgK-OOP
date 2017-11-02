package com.gmail.bicycle.threads;

import java.math.BigInteger;

public class FactorialThread implements Runnable {
	private int number;
	private BigInteger result;

	public FactorialThread(int number) {
		super();
		this.number = number;
	}

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println("I am thread " + thread.getName());
		result = Factorial.calculateLinear(number);
		System.out.println(result);
		System.out.println("End of thread " + thread.getName());
	}
	
	public int getNumber() {
		return number;
	}

	public BigInteger getResult() {
		return result;
	}
	
}
