package com.gmail.bicycle.func;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Main {

	public static void main(String[] args) {
		int[] array = new int[] { 2, -2, 4, 435, -12 };
		System.out.println(findDigit(array));
		String data = "AB ab";

		int[] arr = generateArray(data);
		for (int i : arr) {
			System.out.println(i);
		}

		System.out.println("Max is:");
		int max;
		try {
			max = findMix("in.txt");
			System.out.println(max);
		} catch (NoSuchElementException | IOException e) {
			e.printStackTrace();
		}
	}

	public static int findDigit(int[] array) {
		int result = Arrays.stream(array).
				map(n -> Math.abs(n)).
				min().orElseThrow(NoSuchElementException::new);

		return result;
	}

	public static int[] generateArray(String data) {
		int[] result = data.chars()
				.map(n -> (int) n)
				.toArray();

		return result;
	}

	public static int findMix(String fileName) throws NoSuchElementException, IOException {
		int result = -0;

		result = Files.lines(Paths.get(fileName))
				.map(n -> Integer.parseInt(n))
				.max(Integer::compare)
				.orElseThrow(NoSuchElementException::new);

		return result;
	}

}
