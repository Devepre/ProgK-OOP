package com.gmail.bicycle;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// String delimiter = " ";
		String delimiter = System.lineSeparator();

		String fileNameOne = "G:\\Temp\\1.txt";
		String fileNameTwo = "G:\\Temp\\2.txt";
		String fileNameThree = "G:\\Temp\\3.txt";

		File fileOne = new File(fileNameOne);
		File fileTwo = new File(fileNameTwo);
		File fileThree = new File(fileNameThree);

		try {
			StringBuilder inputData = FileHandler.readTextFromFile(fileOne, delimiter);
			StringBuilder resultData = FileHandler.getEqualsToFile(inputData, fileTwo, delimiter);
			FileHandler.saveTextToFile(resultData, fileThree);
		} catch (IOException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}

}
