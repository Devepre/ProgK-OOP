package com.gmail.bicycle.translation;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class DictionaryMap implements TranslationDictionary, Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, String> dictionary = new HashMap<>();

	public DictionaryMap() {
		super();
	}
	
	@Override
	public void addTranslation(String input, String output) {
		dictionary.put(input, output);
	}

	@Override
	public AbstractMap<String, String> getDictionary() {
		return (AbstractMap<String, String>) dictionary;
	}

	@Override
	public String toString() {
		return dictionary + System.lineSeparator();
	}

}
