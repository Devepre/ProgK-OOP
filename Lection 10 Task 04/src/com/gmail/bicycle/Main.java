package com.gmail.bicycle;

import java.io.IOException;
import java.util.Scanner;

import com.gmail.bicycle.ascii.Alphabet;
import com.gmail.bicycle.ascii.AlphabetGenerator;
import com.gmail.bicycle.ascii.TextProcessor;

public class Main {
	public static final String DEF_MESSAGE = "HELLO WORLD FROM JAVA!";

	public static void main(String[] args) throws IOException {
		String input = null;
		if (args.length > 0) {
			input = args[0];
		} else {
			System.out.println("Please provide your text");
			Scanner sc = new Scanner(System.in);
			input = sc.nextLine();
			sc.close();
		}
		
		Alphabet alphabet = AlphabetGenerator.generateDefault();
		TextProcessor.print(input.toUpperCase(), alphabet);

	}

}
