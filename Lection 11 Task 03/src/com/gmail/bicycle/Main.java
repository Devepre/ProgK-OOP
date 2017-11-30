package com.gmail.bicycle;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: <URL> <path to save file>");
			System.exit(0);
		}
		String urlString = args[0];
		String pathName = args[1];
		
		try {			
			Browser browser = new Browser();
			File fileOutput = new File(pathName);
			FileHandler.writeTextToFile(browser.getListOfURLs(urlString), fileOutput);
			System.out.println("done");
		} catch (IllegalArgumentException | IOException | SAXException | ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

}
