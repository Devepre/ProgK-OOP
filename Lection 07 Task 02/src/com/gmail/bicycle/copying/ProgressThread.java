package com.gmail.bicycle.copying;

public class ProgressThread implements Runnable {
	private Action actionProgress;
	private long fileSize;

	public ProgressThread(Action actionOutput, long fileSize) {
		super();
		this.actionProgress = actionOutput;
		this.fileSize = fileSize;
	}

	@Override
	public void run() {
		int progressLength = 70;
		printPreview(progressLength);

		long savedBytesCount = 0;
		int printedProgressPoints = 0;
		for (; actionProgress.isTurn() || !actionProgress.isStop();) {
			long currentBytesCount = actionProgress.getCounter();
			if (currentBytesCount !=-0) {
				savedBytesCount += currentBytesCount;
				int currentProgressPoints = (int) ((savedBytesCount * progressLength) / this.fileSize);
				for (int i = printedProgressPoints; i < currentProgressPoints; i++) {
					System.out.print("%");
					printedProgressPoints++;
				}				
			}
		}
		System.out.println(System.lineSeparator() + "Done!");

	}

	private void printPreview(int progressLength) {
		System.out.println("Progress:");
		for (int i = 0; i < progressLength; i++) {
			String str = (i == 0 || i == progressLength - 1) ? "|" : "-";
			System.out.print(str);
		}
		System.out.println();
	}

}
