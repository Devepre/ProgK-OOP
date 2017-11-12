package com.gmail.bicycle.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	private static File fileToSearch;
	private static File folder;
	private static int nThreads = 3;

	public static void main(String[] args) {		
		initializeData("E:\\temp\\", "01.txt");
						
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		CompletionService<List<String>> completionService = new ExecutorCompletionService<>(executorService);
		SearchFile thSearch = new SearchFile(fileToSearch, folder, nThreads);
		completionService.submit(thSearch);

		try {
			Future<List<String>> future = completionService.take();
			List<String> found = future.get();
			printResults(found);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}

	}
	
	private static void initializeData(String folderName, String fileName) {		
		folder = new File(folderName);
		fileToSearch = new File(fileName);		
	}
	
	private static void printResults(List<String> found) {
		System.out.println("Searching results:");
		if (found.isEmpty()) {
			System.out.println("Nothing to show");
		}
		for (String string : found) {
			System.out.println(string);
		}
	}

}
