package com.gmail.bicycle.translation;

public class Main {
	public static final String FILE_INPUT = "English.in";
	public static final String FILE_OUTPUT = "Ukrainian.out";
	public static final String FILE_DICTIONARY_TXT = "dictionary.txt";
	public static final String FILE_DICTIONARY_BIN = "dictionary.dat";

	public static void main(String[] args) {
		TranslationDictionary dictionaryMap = DictionaryCreator.loadFromTXTFile(FILE_DICTIONARY);
		DictionaryCreator.saveToFile(dictionaryMap, FILE_DICTIONARY_BIN");
		TranslationDictionary dictionaryMap = DictionaryCreator.loadFromFile(FILE_DICTIONARY_BIN);
		Translator.translate(FILE_INPUT, FILE_OUTPUT, dictionaryMap);

	}

}
