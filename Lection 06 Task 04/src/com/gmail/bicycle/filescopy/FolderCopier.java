package com.gmail.bicycle.filescopy;

import java.io.File;
import java.util.ArrayList;

public class FolderCopier implements Runnable {
	private File folderInput;
	private File folderOutput;
	private int numOfThreads;

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
		this.numOfThreads = threads;
	}

	@Override
	public void run() {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		ArrayList<File> listOfFiles = getListOfFilesInDiretctory(folderInput);
		listOfFiles.sort((fileOne, fileTwo) -> Long.compare(fileOne.length(), fileTwo.length()));

		int size = listOfFiles.size();
		for (int i = 0; i < numOfThreads; i++) {
			ArrayList<File> filesToCopy = new ArrayList<File>();
			for (int j = 0; j < (Math.ceil(size / numOfThreads) / 2); j++) {
				if (listOfFiles.isEmpty()) {
					break;
				}
				filesToCopy.add(listOfFiles.get(0));
				listOfFiles.remove(0);
				if (listOfFiles.isEmpty()) {
					break;
				}
				filesToCopy.add(listOfFiles.get(listOfFiles.size() - 1));
				listOfFiles.remove(listOfFiles.size() - 1);
			}
			if (i == numOfThreads - 1) {
				for (; !listOfFiles.isEmpty();) {
					filesToCopy.add(listOfFiles.get(0));
					listOfFiles.remove(0);
				}
			}
			Thread thread = new Thread(new FileCopier(filesToCopy, folderOutput));
			threads.add(thread);
			System.out.println(filesToCopy.toString() + " -> " + folderOutput.getAbsolutePath());
		}
		startAndJoinThreads(threads);

	}

	private void startAndJoinThreads(ArrayList<Thread> threads) {
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private ArrayList<File> getListOfFilesInDiretctory(File original) {
		ArrayList<File> resultList = new ArrayList<File>(original.listFiles().length);
		for (File file : original.listFiles()) {
			if (file.isFile()) {
				resultList.add(file);
			}
		}
		return resultList;
	}

}
