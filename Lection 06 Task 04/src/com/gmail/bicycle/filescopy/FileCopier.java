package com.gmail.bicycle.filescopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FileCopier implements Runnable {
	private ArrayList<File> filesInput;
	private File folderOutput;

	public FileCopier(ArrayList<File> filesInput, File fileOutput) {
		super();
		this.filesInput = filesInput;
		this.folderOutput = fileOutput;
	}

	@Override
	public void run() {
		for (File file : filesInput) {
			try {
				copyFile(file, folderOutput);
			} catch (IllegalArgumentException | IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void copyFile(File original, File destination) throws IllegalArgumentException, IOException {
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

}
