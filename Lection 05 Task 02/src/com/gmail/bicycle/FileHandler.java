package com.gmail.bicycle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

	private FileHandler() {
		super();
	}

	public static StringBuilder readTextFromFile(File file, String delimiter)
			throws IOException, IllegalArgumentException {
		checkIfDir(file);

		StringBuilder data = new StringBuilder((int) file.length());
		try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
			String str;
			while ((str = bReader.readLine()) != null) {
				data.append(str).append(delimiter);
			}
		}

		return data;
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
