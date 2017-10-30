package com.gmail.bicycle;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public final class FileHandler {
	private static int BUFFER_SIZE = 32_768;
	private boolean firstReursiveIteration = true;
	private String topDirectory;

	public FileHandler() {
		super();
	}

	public static void printListOfFiles(File[] files) {
		for (File file : files) {
			System.out.println(file);
		}
	}

	public static void copyFile(File original, File destination) throws IllegalArgumentException, IOException {
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

	public void copyDirectory(File original, File destination, FileFilter fileFilter)
			throws IllegalArgumentException, IOException, SecurityException {
		if (destination.isFile()) {
			throw new IllegalArgumentException("Can't copy to File instead of Directory");
		}

		if (!original.exists()) {
			throw new IllegalArgumentException("Can't find Directory");
		}

		if (original.isFile()) {
			copyFile(original, destination);
		} else {
			if (firstReursiveIteration) {
				topDirectory = original.getName();
				firstReursiveIteration = false;
			} else {
				String newDestination = destination.getAbsolutePath() + File.separator + original.getName();
				destination = new File(newDestination);

				if (directoryHasFileFiltered(original, fileFilter)) {
					destination.mkdirs();
				}
			}

			for (File file : getDirectoriesPlusFilesFiltered(original, fileFilter)) {
				copyDirectory(file, destination, fileFilter);
			}

			// Top of recursive call if (true)
			// Need to toggle firstIteration to true in order to enable correct using of
			// this FileHandler instance in future
			// Something like finalize() for copyDirectory recursive method
			if (topDirectory.equals(original.getName())) {
				firstReursiveIteration = true;
			}
		}

	}

	protected boolean directoryHasFileFiltered(File original, FileFilter fileFilter) {
		return original.listFiles(fileFilter).length > 0 ? true : false;
	}

	protected File[] getDirectoriesPlusFilesFiltered(File original, FileFilter fileFilter) {
		File[] listOfFilesFiltered = original.listFiles(fileFilter);
		File[] listOfDirectories = getListOfDirectoriesInDiretctory(original);
		File[] combined = new File[listOfFilesFiltered.length + listOfDirectories.length];
		int counter = 0;

		for (; counter < listOfFilesFiltered.length; counter++) {
			combined[counter] = listOfFilesFiltered[counter];
		}
		for (int i = 0; i < listOfDirectories.length; i++) {
			if (listOfDirectories[i] != null) {
				combined[counter++] = listOfDirectories[i];
			}
		}

		return combined;
	}

	protected File[] getListOfDirectoriesInDiretctory(File original) {
		File[] tempList = new File[original.listFiles().length];
		int i = 0;
		for (File file : original.listFiles()) {
			if (file.isDirectory()) {
				tempList[i++] = file;
			}
		}
		File[] resultList = Arrays.copyOf(tempList, i);
		tempList = null;

		return resultList;
	}

}
