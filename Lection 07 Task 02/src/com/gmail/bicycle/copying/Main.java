package com.gmail.bicycle.copying;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		final int BUFFER_SIZE = 4096 * 4096;
		
		String fileInputName = "E:\\temp\\txt\\05.mp4";
		File fileOriginal = new File(fileInputName);
		String fileOutputName = "G:\\temp\\02.mp4";
		File fileOutput = new File(fileOutputName);

		Action actionInput = new Action();
		Action actionProgress = new Action();
		InputThread inputThread = new InputThread(actionInput, fileOriginal, BUFFER_SIZE);
		OutputThread outputThread = new OutputThread(actionInput, actionProgress, fileOutput);
		ProgressThread progressThread = new ProgressThread(actionProgress, fileOriginal.length());

		Thread threadInput = new Thread(inputThread, "Input Thread");
		Thread threadOutput = new Thread(outputThread, "Output Thread");
		Thread threadProgress = new Thread(progressThread, "Progress Thread");
		threadInput.start();
		threadOutput.start();
		threadProgress.start();
	}

}
