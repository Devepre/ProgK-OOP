package com.gmail.bicycle.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchFile implements Callable<List<String>> {
	private int nThreads = 1;
	private ExecutorService executorService;
	private File searchFile;
	private File startFolder;
	private List<File> listOfFolders = new ArrayList<>();
	private List<String> result = new ArrayList<>();

	public SearchFile(File searchFile, File startFolder, int nThreads) {
		super();
		if (searchFile.isDirectory() || startFolder.isFile() || !startFolder.exists()) {
			throw new IllegalArgumentException("wrong parameter for searching");
		}
		this.searchFile = searchFile;
		this.startFolder = startFolder;
		this.nThreads = nThreads;
	}

	@Override
	public List<String> call() throws Exception {
		this.listOfFolders.add(this.startFolder);
		getListOfFolders(this.startFolder);

		executorService = Executors.newFixedThreadPool(this.nThreads);
		for (File fileFolder : this.listOfFolders) {
			FileSearcher fSearcher = new FileSearcher(fileFolder);
			this.executorService.submit(fSearcher);
		}
		this.executorService.shutdown();

		for (; !this.executorService.isTerminated();) {
			;
		}

		return this.result;
	}

	private void getListOfFolders(File startFolder) {
		File[] list = startFolder.listFiles();
		for (File fileCurrent : list) {
			if (fileCurrent.isDirectory()) {
				this.listOfFolders.add(fileCurrent);
				getListOfFolders(fileCurrent);
			}
		}
	}

	private class FileSearcher implements Runnable {
		private File startFolder;

		public FileSearcher(File startFolder) {
			super();
			this.startFolder = startFolder;
		}

		@Override
		public void run() {
			searchFileInFolder(SearchFile.this.searchFile, this.startFolder);
		}

		private void searchFileInFolder(File searchFile, File startFolder) {
			for (File file : startFolder.listFiles()) {
				if (file.isFile() && file.getName().equals(searchFile.getName())) {
					printFileInfo(file);
				}
			}

		}

		private void printFileInfo(File file) {
			// System.out.println(file.getAbsolutePath());	//can be used with Runnable implementation
			SearchFile.this.result.add(file.getAbsolutePath());
		}

	}

}
