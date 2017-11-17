package com.gmail.bicycle.letters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

	private FileHandler() {
		super();
	}

	public static String readTextFromFile(File file)
			throws IOException, IllegalArgumentException {
		performChecks(file);

		StringBuilder data = new StringBuilder((int) file.length());
		try (BufferedReader bReader = new BufferedReader(new FileReader(file))) {
			String str;
			while ((str = bReader.readLine()) != null) {
				data.append(str);
			}
		}

		return data.toString();
	}
	
	protected static void performChecks(File file) throws IllegalArgumentException {
		if (file.isDirectory()) {
			throw new IllegalArgumentException(file.getAbsolutePath() + " (argument can't be directory)");
		}
		if (!file.exists()) {
			throw new IllegalArgumentException("File doesn't exists");
		}
	}
}
