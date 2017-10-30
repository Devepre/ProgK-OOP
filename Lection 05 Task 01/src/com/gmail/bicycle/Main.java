package com.gmail.bicycle;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String pathFolderOrig = "/home/devepr/Documents/TiJ4/";
		String pathFolderDest = "/home/devepr/Videos/";
		String[] extensions = new String[] { "py", "txt" };

		System.out.println("Hi there. Let's start copying...");
		System.out.printf("From %s\nTo %s\nFile extensions: %s\n\n", pathFolderOrig, pathFolderDest,
				Arrays.toString(extensions));

		File directoryOrig = new File(pathFolderOrig);
		File directoryDest = new File(pathFolderDest);

		FileHandler fHandler = new FileHandler();
		System.out.println("First let's use Lambda version");
		try {
			fHandler.copyDirectory(directoryOrig, directoryDest, file -> {
				if (file.isFile()) {
					String fileName = file.getName();
					String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
					for (String string : extensions) {
						if (string.equals(fileExtension)) {
							return true;
						}
					}
					return false;
				} else {
					return false;
				}
			});
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done. Please check output folder.");
		System.out.println("Please delete files and press Enter to continue");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		sc.close();

		System.out.println("Alternatively using Class implementation version");
		CustomFileFilter cff = new CustomFileFilter(extensions);
		try {
			fHandler.copyDirectory(directoryOrig, directoryDest, cff);
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done! Please check your folder again.");

	}

}
