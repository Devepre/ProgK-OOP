package com.gmail.bicycle.translation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

	private FileHandler() {
		super();
	}

	public static List<String> readTextFromFile(File file, String delimiter)
			throws IOException, IllegalArgumentException {
		checkIfDir(file);

		List<String> result = new ArrayList<>();
		try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
			String str;
			while ((str = bReader.readLine()) != null) {
				result.add(str);
			}
		}

		return result;
	}

	public static StringBuilder getEqualsToFile(StringBuilder data, File file, String delimiter)
			throws IOException, IllegalArgumentException {
		checkIfDir(file);

		StringBuilder equals = new StringBuilder(data.length());
		try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
			String str;
			while ((str = bReader.readLine()) != null) {
				String[] words = str.split("\\W+");
				for (String string : words) {
					if (!string.equals("") && data.indexOf(string) > -1) {
						equals.append(string).append(delimiter);
					}
				}
			}
		}

		return equals;
	}

	public static void saveTextToFile(StringBuilder data, File file) throws IOException, IllegalArgumentException {
		checkIfDir(file);

		try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(file))) {
			bWriter.write(data.toString());
		}

	}

	protected static void checkIfDir(File file) throws IllegalArgumentException {
		if (file.isDirectory()) {
			throw new IllegalArgumentException(file.getAbsolutePath() + " (argument can't be directory)");
		}
	}
}
