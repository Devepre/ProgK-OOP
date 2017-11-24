package com.gmail.bicycle.translation;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DictionaryCreator {
	public static final String SEPARATOR = ",";
	
	public static TranslationDictionary loadFromFile(String fileName) {
		TranslationDictionary dictionaryMap = new DictionaryMap();
		try {
			List<String> lines = FileHandler.readTextFromFile(new File(fileName), SEPARATOR);
			
			for (String string : lines) {
				String[] words = string.toString().split(SEPARATOR);
				dictionaryMap.addTranslation(words[0], words.length == 1 ? "" : words[1]);				
			}			
			
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		
		return dictionaryMap;
	}
	

}
