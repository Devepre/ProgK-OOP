package com.gmail.bicycle;

import java.util.HashMap;
import java.util.Map;

public class Alphabet {
	Map<Character, Letter> alphabet = new HashMap<>();
	int maxLetterHeight;

	public Alphabet() {
		super();
	}

	public void addLetter(char key, Letter value) {
		this.alphabet.put(key, value);
		this.maxLetterHeight = value.getHeight() > this.maxLetterHeight ?
				value.getHeight() 
				: this.maxLetterHeight;
	}

	public int getMaxLetterHeight() {
		return maxLetterHeight;
	}

	public Letter get(Character key) {
		Letter result = alphabet.get(key);
		
		return result;
	}
	
}
