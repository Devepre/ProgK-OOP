package com.gmail.bicycle.filescopy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FolderCopier implements Runnable {
	private File folderInput;
	private File folderOutput;
	private int threads;

	public FolderCopier() {
		super();
	}

	public FolderCopier(File folderInput, File folderOutput, int threads) {
		super();
		if (folderInput == null || folderOutput == null) {
			throw new IllegalArgumentException();
		}
		this.folderInput = folderInput;
		this.folderOutput = folderOutput;
		this.threads = threads;
	}

	@Override
	public void run() {
		File[] listOfFiles = getListOfFilesInDiretctory(folderInput);
		Arrays.sort(listOfFiles, (fileOne, fileTwo) -> Long.compare(fileOne.length(), fileTwo.length()));

		for (int i = 0; i < listOfFiles.length / 2; i++) {
			int count = (listOfFiles.length) / threads;
			int index = i;
			ArrayList<File> filesToCopy = new ArrayList<File>(count);
			for (int j = 0; j < count / 2; j++) {
				index = i + j;
				filesToCopy.add(listOfFiles[index]);
				index = listOfFiles.length - i - 1;
				filesToCopy.add(listOfFiles[index]);
			}
			System.out.println(filesToCopy.toString());

			FileCopier fileCopier = new FileCopier(filesToCopy, folderOutput);
			Thread thread = new Thread(fileCopier);
			//thread.start();

		}
	}

	private void folderCopy() throws IllegalArgumentException, IOException {

	}

	private File[] getListOfFilesInDiretctory(File original) {
		File[] tempList = new File[original.listFiles().length];
		int i = 0;
		for (File file : original.listFiles()) {
			if (file.isFile()) {
				tempList[i++] = file;
			}
		}
		File[] resultList = Arrays.copyOf(tempList, i);
		tempList = null;

		return resultList;
	}

}
