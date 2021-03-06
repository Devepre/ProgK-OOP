package com.gmail.bicycle.translation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Optional;

public class Translator {
	private static final String REG_EXP_WORDS = "[\\p{Punct}\\s]+";

	public static void translate(String inputFileName, String outputFileName, TranslationDictionary dictionaryMap) {
		AbstractMap<String, String> dictionary = dictionaryMap.getDictionary();

		try (PrintWriter printWriter = new PrintWriter(new FileWriter(outputFileName))) {
			Path path = Paths.get(inputFileName);
			Files.lines(path)
					.map(line -> line.split(REG_EXP_WORDS))
					.flatMap(Arrays::stream)
					.map(word -> word.toLowerCase())
					.map(word -> dictionary.get(word))
					.map(word -> Optional.ofNullable(word))
					.map(word -> word.orElse("<untranslated>"))
					.forEach(word -> printWriter.write(word + " "));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
