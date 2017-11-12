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

	public static void main(String[] args) {
		int nThreads = 3;
		String folderName = "E:\\temp\\";
		File folder = new File(folderName);
		String fileName = "01.txt";
		File fileToSearch = new File(fileName);
		List<String> found = new ArrayList<>();

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		CompletionService<List<String>> completionService = new ExecutorCompletionService<>(executorService);
		SearchFile thSearch = new SearchFile(fileToSearch, folder, nThreads);
		completionService.submit(thSearch);

		try {
			Future<List<String>> future = completionService.take();
			found = future.get();
			System.out.println(found);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}

	}

}
