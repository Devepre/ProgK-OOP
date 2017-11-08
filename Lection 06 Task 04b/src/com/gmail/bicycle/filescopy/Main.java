package com.gmail.bicycle.filescopy;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	final static int NUM_OF_THREADS = 3;

	public static void main(String[] args) {
		String folderInputName = "E:\\temp\\txt\\";
		String folderOutputName = "G:\\Temp\\";
		File folderOriginal = new File(folderInputName);
		File folderDestination = new File(folderOutputName);

		ArrayList<File> listOfFiles = getListOfFilesInDiretctory(folderOriginal);

		ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
		CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

		try {
			for (File file : listOfFiles) {
				FileCopyThread fileCopyThread = new FileCopyThread(file, folderDestination);
				completionService.submit(fileCopyThread);
			}

			for (int i = 0; i < listOfFiles.size(); i++) {
				try {
					Future<String> future = completionService.take();
					System.out.println(future.get());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}

			}
		} finally {
			executorService.shutdown();
		}
		System.out.println("The end");
	}

	private static ArrayList<File> getListOfFilesInDiretctory(File original) {
		ArrayList<File> resultList = new ArrayList<File>(original.listFiles().length);
		for (File file : original.listFiles()) {
			if (file.isFile()) {
				resultList.add(file);
			}
		}
		return resultList;
	}

}
