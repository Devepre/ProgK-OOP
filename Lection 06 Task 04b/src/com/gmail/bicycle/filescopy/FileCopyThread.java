package com.gmail.bicycle.filescopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

public class FileCopyThread implements Callable<String> {
	private File original;
	private File destination;

	public FileCopyThread() {
		super();
	}

	public FileCopyThread(File original, File destination) {
		super();
		this.original = original;
		this.destination = destination;
	}

	@Override
	public String call() throws Exception {
		try {
			copyFile();
			return this.toString();
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	private void copyFile() throws IllegalArgumentException, IOException {
		final int BUFFER_SIZE = 4096;
		if (original.isDirectory() || destination.isFile()) {
			throw new IllegalArgumentException("Can copy only File to Directory");
		}

		if (!original.exists() || !destination.exists()) {
			throw new IllegalArgumentException("Directory of File doesn't exist");
		}

		String destinationFullName = destination.getAbsolutePath() + File.separator + original.getName();

		try (InputStream fis = new FileInputStream(original);
				FileOutputStream fos = new FileOutputStream(destinationFullName)) {
			byte[] dataBuffer = new byte[BUFFER_SIZE];
			int count = 0;
			while ((count = fis.read(dataBuffer)) > 0) {
				fos.write(dataBuffer, 0, count);
			}
		} catch (IOException e) {
			throw e;
		}

	}

	@Override
	public String toString() {
		return "Copied from " + original.getAbsolutePath() + " to " + destination.getAbsolutePath();
	}

}
