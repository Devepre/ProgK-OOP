package com.gmail.bicycle;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileHandler {

	private FileHandler() {
		super();
	}

	public static void writeTextToFile(List<String> data, File file) throws IOException, IllegalArgumentException {
		checkIfDir(file);
		if (data == null) {
			throw new IllegalArgumentException();
		}

		try (PrintWriter pw = new PrintWriter(file)) {
			for (String line : data) {
				pw.println(line);
			}
		}

	}

	protected static void checkIfDir(File file) throws IllegalArgumentException {
		if (file.isDirectory()) {
			throw new IllegalArgumentException(file.getAbsolutePath() + " (argument can't be directory)");
		}
	}
}