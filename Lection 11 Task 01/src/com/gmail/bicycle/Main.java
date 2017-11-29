package com.gmail.bicycle;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String input = null;
		if (args.length > 0) {
			input = args[0];
		} else {
			System.out.println("Please provide file name with list of resources to check");
			Scanner sc = new Scanner(System.in);
			input = sc.nextLine();
			sc.close();
		}
		
		try {
			List<String> listOfResources = FileHandler.readTextFromFile(new File(input));
			String status = null;
			HTTPCheck checker = new HTTPCheck();
			for (String urlAddress : listOfResources) {
				System.out.println(urlAddress + "\t\t" + checker.checkURL(urlAddress));
			}
			
			//another approach to iterate just to practice
//			List<String> results = checker.checkURLs(listOfResources);
//			Iterator<String> itrResults = results.iterator();
//			for (String urlAddress : listOfResources) {
//				System.out.println(urlAddress + "\t\t" + itrResults.next());
//			}
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}

}
