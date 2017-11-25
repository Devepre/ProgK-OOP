package com.gmail.bicycle;

public class Run {

	public static void main(String[] args) {
		String[] strA = new String[] {
				"   *   ",
				"  * *  ",
				" * * * ",
				"*     *",
				"*     *"
		};
		
		"     _      ", 
		"    / \\    ", 
		"   / _ \\   ", 
		"  / ___ \\  ", 
		" /_/   \\_\\", 
		
		String[] strB = new String[] {
				"***  ",
				"* *  ",
				"***  ",
				"*  **",
				"*****"
		};
		
		Letter letterA = new Letter(strA);
		Letter letterB = new Letter(strB);
		
		Alphabet alphabet = new Alphabet();
		alphabet.addLetter('A', letterA);
		alphabet.addLetter('B', letterB);
		
		String text = "BA";
		print(text, alphabet);

	}
	
	public static void print(String text, Alphabet alphabet) {
		char[] chArray = text.toCharArray();
		int height = alphabet.getMaxLetterHeight();
		StringBuilder[] sb = new StringBuilder[height];
		
		for (int i = 0; i < height; i++) {
			sb[i] = new StringBuilder();
			for (Character letter : chArray) {
				sb[i].append(alphabet.get(letter).getString(i));
				sb[i].append(" ");
			}
		}
		
		for (StringBuilder stringBuilder : sb) {
			System.out.println(stringBuilder);
		}
	}

}
