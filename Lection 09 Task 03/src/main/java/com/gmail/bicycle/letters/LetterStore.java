package com.gmail.bicycle.letters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LetterStore {
	private static final int SIZE = 26;
	private static final char START_REGULAR_CHAR = 'a';
	private static final char START_CAPS_CHAR = 'A';
	private List<Letter> storage;

	public LetterStore() {
		super();
		initialize();
	}

	private final void initialize() {
		storage = new ArrayList<>(SIZE);
		for (char ch = START_REGULAR_CHAR; ch < START_REGULAR_CHAR + SIZE; ch++) {
			storage.add(new Letter(ch));
		}
		for (char ch = START_CAPS_CHAR; ch < START_CAPS_CHAR + SIZE; ch++) {
			storage.add(new Letter(ch));
		}
	}

	public void sort() {
		this.storage.sort(null);
	}

	public void countFromFile(File file) {
		try {
			String data = FileHandler.readTextFromFile(file);
			char[] chars = data.toCharArray();
			for (char ch : chars) {
				Letter comparedLetter = new Letter(ch);
				if (storage.contains(comparedLetter)) {
					Letter letter = storage.get(storage.indexOf(comparedLetter));
					letter.setCount(letter.getCount() + 1);
				}
			}

		} catch (IllegalArgumentException | IOException e) {
			System.out.println("Something wrong with file provided");
			e.printStackTrace();
		}
	}

	public void printReversed() {
//		usual method
//		Collections.reverse(storage);
//		for (Letter letter : storage) {
//			if (letter.getCount() > 0) {
//				System.out.println(letter);
//			}
//		}
//		Collections.reverse(storage);
		
//		Stream API v1
//		List<Letter> result = storage.stream()
//				.filter(n -> n.getCount() > 0)
//				.sorted(Collections.reverseOrder())
//				.collect(Collectors.toCollection(ArrayList::new));
//		System.out.println(result);
				
//		Stream API v2		
		storage.stream()		
		    .sorted(Collections.reverseOrder())
		    .filter(n -> n.getCount() > 0)
		    .forEach(System.out::println);
				
	}

	@Override
	public String toString() {
		return "LetterStore:" + storage;
	}

}
