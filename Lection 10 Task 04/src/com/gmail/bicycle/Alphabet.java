package com.gmail.bicycle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

	public Optional<Letter> get(Character key) {
		Optional<Letter> result = Optional.ofNullable(alphabet.get(key));
		
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alphabet == null) ? 0 : alphabet.hashCode());
		result = prime * result + maxLetterHeight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alphabet other = (Alphabet) obj;
		if (alphabet == null) {
			if (other.alphabet != null)
				return false;
		} else if (!alphabet.equals(other.alphabet))
			return false;
		if (maxLetterHeight != other.maxLetterHeight)
			return false;
		return true;
	}
	
}
