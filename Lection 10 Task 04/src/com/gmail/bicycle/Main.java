package com.gmail.bicycle;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		Map<Integer, String> letterA = new HashMap<>();
		letterA.put(0, "   *   ");
		letterA.put(1, "  * *  ");
		letterA.put(2, " * * * ");
		letterA.put(3, "*     *");
		letterA.put(4, "*     *");

		Map<Integer, String> letterB = new HashMap<>();
		letterB.put(0, "***  ");
		letterB.put(1, "* *  ");
		letterB.put(2, "***  ");
		letterB.put(3, "*  **");
		letterB.put(4, "*****");
		
		Map<Integer, String> letterC = new HashMap<>();
		letterC.put(0, "*****");
		letterC.put(1, "*    ");
		letterC.put(2, "*    ");
		letterC.put(3, "*    ");
		letterC.put(4, "*****");

		Map<Character, Map<Integer, String>> alphabet = new HashMap<>();
		alphabet.put('A', letterA);
		alphabet.put('B', letterB);
		alphabet.put('C', letterC);

//		letterA.forEach((key, value) -> System.out.println(value));
//		letterB.forEach((key, value) -> System.out.println(value));

		String text = "AC";
		print(text, alphabet);

	}
	
	public static void print(String text, Map<Character, Map<Integer, String>> alphabet) {
		char[] chArray = text.toCharArray();
		int height = alphabet.get('A').size();
		StringBuilder[] sb = new StringBuilder[height];
		
		for (int i = 0; i < height; i++) {
			sb[i] = new StringBuilder();
			for (Character letter : chArray) {
				sb[i].append(alphabet.get(letter).get(i)).append(" ");
			}
//			sb[i].append(alphabet.get('A').get(i)).append(alphabet.get('B').get(i)) ;
		}
		
		for (StringBuilder stringBuilder : sb) {
			System.out.println(stringBuilder);
		}

	}

}
