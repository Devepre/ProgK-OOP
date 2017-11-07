package com.gmail.bicycle.filescopy;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		final int NUM_OF_THREADS = 3;
		
		String folderInputName = "E:\\temp\\txt\\";
		String folderOutputName = "G:\\Temp\\";
		
		File folderOriginal = new File(folderInputName);
		File folderDestination = new File(folderOutputName);

		FolderCopier copier = new FolderCopier(folderOriginal, folderDestination, NUM_OF_THREADS);
		Thread thread = new Thread(copier);
		thread.start();
		System.out.println("Copying is in progress...");
		try {
			thread.join();			
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		System.out.println("The End");
	}

}
