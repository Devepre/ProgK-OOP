package com.gmail.bicycle.ascii;

public class TextProcessor {
	public static void print(String text, Alphabet alphabet) {
		char[] chArray = text.toCharArray();
		int height = alphabet.getMaxLetterHeight();
		StringBuilder[] sb = new StringBuilder[height];
		
		for (int i = 0; i < height; i++) {
			sb[i] = new StringBuilder();
			for (Character letter : chArray) {
				if (alphabet.get(letter).isPresent()) {
					Letter currentLetter = alphabet.get(letter).get();
					String currentLetterString = currentLetter.getString(i);
					sb[i].append(currentLetterString);
					sb[i].append(" ");
				} else {
					sb[i].append("");
				}
			}
		}
		
		for (StringBuilder stringBuilder : sb) {
			System.out.println(stringBuilder);
		}
	}
}
