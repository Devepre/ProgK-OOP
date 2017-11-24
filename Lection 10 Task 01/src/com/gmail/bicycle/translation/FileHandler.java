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

	protected static void checkIfDir(File file) throws IllegalArgumentException {
		if (file.isDirectory()) {
			throw new IllegalArgumentException(file.getAbsolutePath() + " (argument can't be directory)");
		}
	}
}
