package com.gmail.bicycle.translation;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class DictionaryMap implements TranslationDictionary {
	private Map<String, String> dictionary = new HashMap<>();

	public DictionaryMap() {
		super();
	}
	
	@Override
	public void addTranslation(String input, String output) {
		dictionary.put(input, output);
	}

	public AbstractMap<String, String> getDictionary() {
		return (AbstractMap<String, String>) dictionary;
	}

	@Override
	public String toString() {
		return dictionary + System.lineSeparator();
	}

}
