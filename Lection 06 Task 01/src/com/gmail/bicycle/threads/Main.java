package com.gmail.bicycle.threads;

import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		System.out.println("Main start");
		
		//BigInteger bigInt = Factorial.calculateRecursively(20000); //stack overflow		
		//System.out.println(bigInt);
		
		int number = 100;
		
		FactorialThread factorialThread = new FactorialThread(number);
		System.out.println("Main: result is " + factorialThread.getResult());
		Thread thread = new Thread(factorialThread);		
		thread.start();
		
//		try {
//			thread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		System.out.println("Main: result is " + factorialThread.getResult());
		
		System.out.println("End of main");

	}

}
