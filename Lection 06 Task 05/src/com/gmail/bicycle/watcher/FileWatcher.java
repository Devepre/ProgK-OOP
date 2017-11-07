package com.gmail.bicycle.watcher;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class FileWatcher implements Runnable {
	private static final int TIME = 1000;
	private File mainFolder;

	public FileWatcher() {
		super();
	}

	public FileWatcher(File mainFolder) {
		super();
		if (mainFolder == null || !mainFolder.exists()) {
			throw new IllegalArgumentException("Wrong folder");
		}
		this.mainFolder = mainFolder;
	}

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy  hh:mm:ss");

		String[] listOfPreviousFiles = this.mainFolder.list();
		String[] listOfCurrentFiles;		
		for (; !thread.isInterrupted();) {
			int renamedFlag = 0;
			listOfCurrentFiles = mainFolder.list();
			ArrayList<String> difference = compareRemovedLists(listOfPreviousFiles, listOfCurrentFiles);
			if (!difference.isEmpty()) {
				renamedFlag++;
				for (String string : difference) {
					System.out.print(sdf.format(System.currentTimeMillis()));
					System.out.println(" removed:\t" + string);
				}
			}

			difference = compareAddedLists(listOfPreviousFiles, listOfCurrentFiles);
			if (!difference.isEmpty()) {
				renamedFlag++;
				for (String string : difference) {
					System.out.print(sdf.format(System.currentTimeMillis()));
					System.out.print(" added:\t" + string);
				}
			}

			if (renamedFlag == 2) {
				System.out.println(" [actually it was renamed]");
			} else {
				if (renamedFlag == 1) {
					System.out.println();
				}
			}
			listOfPreviousFiles = listOfCurrentFiles;
			try {
				Thread.sleep(TIME);
			} catch (InterruptedException e) {
				break;
			}
		}
		System.out.println("Folder wacher stopped.");
	}

	private ArrayList<String> compareAddedLists(String[] listOfPreviousFiles, String[] listOfCurrentFiles) {
		ArrayList<String> result = new ArrayList<String>();
		String listPrev = Arrays.toString(listOfPreviousFiles);
		for (String string : listOfCurrentFiles) {
			if (listPrev.indexOf(string) == -1) {
				result.add(string);
			}

		}

		return result;
	}

	private ArrayList<String> compareRemovedLists(String[] listOfPreviousFiles, String[] listOfCurrentFiles) {
		ArrayList<String> result = new ArrayList<String>();
		String listCurrent = Arrays.toString(listOfCurrentFiles);
		for (String string : listOfPreviousFiles) {
			if (listCurrent.indexOf(string) == -1) {
				result.add(string);
			}
		}

		return result;
	}

}
