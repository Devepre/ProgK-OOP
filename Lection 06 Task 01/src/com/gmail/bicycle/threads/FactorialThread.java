package com.gmail.bicycle.threads;

import java.math.BigInteger;

public class FactorialThread implements Runnable {
	private int number;
	private BigInteger result;
	private String name;

	public FactorialThread(int number) {
		super();
		this.number = number;
	}

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		this.name = thread.getName();
		this.result = Factorial.calculateLinear(number);
	}
	
	public int getNumber() {
		return number;
	}

	public BigInteger getResult() {
		return result;
	}

	public String getName() {
		return name;
	}
	
}
