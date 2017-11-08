package com.gmail.bicycle.filescopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

		// using Runnable interface
		for (File file : listOfFiles) {
			completionService.submit(() -> {
				try {
					copyFile(file, folderDestination);
				} catch (IllegalArgumentException | IOException e) {
					e.printStackTrace();
				}
			}, "Copied from " + file.getAbsolutePath() + " to " + folderDestination.getAbsolutePath());
		}

		// using Callable interface
		// for (File file : listOfFiles) {
		// completionService.submit(()-> {
		// copyFile(file, folderDestination);
		// return "Copied from " + file.getAbsolutePath() + " to " +
		// folderDestination.getAbsolutePath();
		// });
		//
		// }

		for (int i = 0; i < listOfFiles.size(); i++) {
			try {
				Future<String> future = completionService.take();
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

		}
		executorService.shutdown();

		System.out.println("Inside Main v3");
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

	private static void copyFile(File original, File destination) throws IllegalArgumentException, IOException {
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
