package com.gmail.bicycle.translation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class DictionaryCreator {
	public static final String SEPARATOR = ",";

	public static void addToDictionary(TranslationDictionary dictionary, String input, String translation) {
		dictionary.addTranslation(input, translation);
	}
	
	public static TranslationDictionary loadFromTXTFile(String fileName) {
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

	public static void saveToFile(TranslationDictionary dictionary, String fileName) {
		if (dictionary == null) {
			throw new NullPointerException("Can't save null");
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			oos.writeObject(dictionary);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static TranslationDictionary loadFromFile(String fileName) {
		if (fileName.isEmpty()) {
			throw new IllegalArgumentException("wrong parameter");
		}
		TranslationDictionary dictionaryMap = new DictionaryMap();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
			dictionaryMap = (TranslationDictionary) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dictionaryMap;
	}

}
