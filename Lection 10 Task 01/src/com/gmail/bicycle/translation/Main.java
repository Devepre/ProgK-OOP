package com.gmail.bicycle.translation;

public class Main {
	public static final String FILE_INPUT = "English.in";
	public static final String FILE_OUTPUT = "Ukrainian.out";
	public static final String FILE_DICTIONARY = "dictionary.txt";

	public static void main(String[] args) {
		TranslationDictionary dictionaryMap = DictionaryCreator.loadFromFile(FILE_DICTIONARY);
		Translator.translate(FILE_INPUT, FILE_OUTPUT, dictionaryMap);

	}

}
