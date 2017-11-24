package com.gmail.bicycle.translation;

import java.io.Serializable;
import java.util.AbstractMap;

public interface TranslationDictionary extends Serializable {
	public void addTranslation(String input, String output);
	public AbstractMap<String, String> getDictionary();

}
