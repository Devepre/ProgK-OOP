package com.gmail.bicycle;

import com.gmail.bicycle.translation.DictionaryCreator;
import com.gmail.bicycle.translation.TranslationDictionary;
import com.gmail.bicycle.translation.Translator;

public class Main {
	public static final String FILE_INPUT = "English.in";
	public static final String FILE_OUTPUT = "Ukrainian.out";
	public static final String FILE_DICTIONARY_TXT = "dictionary.txt";
	public static final String FILE_DICTIONARY_BIN = "dictionary.dat";

	public static void main(String[] args) {
//		TranslationDictionary dictionaryMap = DictionaryCreator.loadFromTXTFile(FILE_DICTIONARY_TXT);
		TranslationDictionary dictionaryMap = DictionaryCreator.loadFromFile(FILE_DICTIONARY_BIN);
		DictionaryCreator.addToDictionary(dictionaryMap, "end", "κ³νεφό");
		DictionaryCreator.saveToFile(dictionaryMap, FILE_DICTIONARY_BIN);		
		Translator.translate(FILE_INPUT, FILE_OUTPUT, dictionaryMap);

	}

}
