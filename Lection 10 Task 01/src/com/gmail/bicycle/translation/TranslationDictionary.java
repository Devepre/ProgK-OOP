package com.gmail.bicycle.translation;

import java.util.AbstractMap;

public interface TranslationDictionary {
	public void addTranslation(String input, String output);
	public AbstractMap<String, String> getDictionary();

}
