package com.gmail.bicycle.watcher;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class FileWatcher implements Runnable {
	private static final int TIME = 1000;

	private File mainFolder;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy  hh:mm:ss");
	private String[] listOfPreviousFiles;
	private String[] listOfCurrentFiles;
	private int renamedFlag;

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
		this.listOfPreviousFiles = this.mainFolder.list();
		System.out.println("Folder watcher started for " + this.mainFolder.getAbsolutePath() + " folder");

		ArrayList<String> difference;
		for (; !thread.isInterrupted();) {
			renamedFlag = 0;
			listOfCurrentFiles = mainFolder.list();

			difference = compareRemovedLists();
			checkDifference(difference, false);

			difference = compareAddedLists();
			checkDifference(difference, true);

			switch (this.renamedFlag) {
			case 2:
				System.out.print(" [actually it was renamed]");
			case 1:
				System.out.println();
			}

			this.listOfPreviousFiles = this.listOfCurrentFiles;

			try {
				Thread.sleep(TIME);
			} catch (InterruptedException e) {
				break;
			}
		}
		System.out.println("Folder watcher stopped.");
	}

	private void checkDifference(ArrayList<String> difference, boolean added) {
		if (!difference.isEmpty()) {
			this.renamedFlag++;

			for (String string : difference) {
				System.out.print(this.sdf.format(System.currentTimeMillis()));
				System.out.print(added ? " added\t" + string : " removed:\t" + string + System.lineSeparator());
			}
		}
	}

	private ArrayList<String> compareAddedLists() {
		ArrayList<String> result = new ArrayList<String>();
		String listPrev = Arrays.toString(listOfPreviousFiles);
		for (String string : listOfCurrentFiles) {
			if (listPrev.indexOf(string) == -1) {
				result.add(string);
			}

		}

		return result;
	}

	private ArrayList<String> compareRemovedLists() {
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
