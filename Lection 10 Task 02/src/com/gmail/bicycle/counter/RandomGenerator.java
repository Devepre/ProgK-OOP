package com.gmail.bicycle.counter;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomGenerator {
	public static Integer[] generateIntegerArray(int size) {
		Integer array[] = IntStream.range(0, size)
			.map(element -> element = (int)(Math.random() * 10))
			.boxed()
			.toArray(Integer[]::new);
		
		return array;
	}

	// this is pseudo-generator, just to fun
	public static String[] generateStringArray(int size, int randomN) {	
		String[] result = new String[size];
		 final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJLMNOPQRSTUVWXYZ ";
	       StringBuilder sb = new StringBuilder();
	       Random rnd = new Random();
	       
	       for(int i = 0; i < size; i++) {
	    	   for (int j = 0; j < rnd.nextInt(randomN); j++ ) {
		           sb.append(characters.charAt(rnd.nextInt(characters.length())));
		       }
	    	   result[i] = sb.toString();
	       }
	       
	       return result;
	}
}
