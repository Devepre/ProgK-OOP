package com.gmail.bicycle.filescopy;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		String folderInputName = "E:\\temp\\txt\\";
		String folderOutputName = "G:\\Temp\\";
		File folderOriginal = new File(folderInputName);
		File folderDestination = new File(folderOutputName);

		FolderCopier copier = new FolderCopier(folderOriginal, folderDestination, 2);
		Thread thread = new Thread(copier);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		System.out.println("The End");
	}

}
