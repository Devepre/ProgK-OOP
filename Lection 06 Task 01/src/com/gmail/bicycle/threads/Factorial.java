package com.gmail.bicycle.threads;

import java.math.BigInteger;

public class Factorial {

	private Factorial() {
		super();	
	}
	
	public static BigInteger calculateRecursively(int number) {
		checkInput(number);
		
		if (number == 1) {
			return new BigInteger("1");
		} else {
			return new BigInteger(String.valueOf(number)).multiply(calculateRecursively(number - 1));
		}
				
	}
	
	public static BigInteger calculateLinear (int number) {
		checkInput(number);
		
		BigInteger bigInteger = new BigInteger(String.valueOf(1));
		for (int i = 2; i <= number; i++) {
			bigInteger = bigInteger.multiply(new BigInteger(String.valueOf(i)));			
		}
		return bigInteger;
	}
	
	private static final void checkInput(int number) {
		if (number < 1) {
			throw new IllegalArgumentException("Number should be positive :)");
		}
	}
	
}
