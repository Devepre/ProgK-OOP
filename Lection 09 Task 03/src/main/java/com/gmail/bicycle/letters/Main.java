package com.gmail.bicycle.letters;

import java.io.File;

public class Main {
	private static final String DEFAULT_FILE_NAME = "a.txt";

	public static void main(String[] args) {		
		LetterStore letterStore = new LetterStore();
		String fileName = args.length == 0 ? DEFAULT_FILE_NAME : args[0];
		letterStore.countFromFile(new File(fileName));
		letterStore.sort();		
		letterStore.printReversed();
	}

}
