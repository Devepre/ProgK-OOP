package com.gmail.bicycle.threads.drivers;

import java.math.BigInteger;
import java.util.Random;

public class ArrayHandler {
	public static int[] generateArray(int size, int minValue, int maxValue) {
		int[] array = new int[size];

		Random rnd = new Random(System.currentTimeMillis());
		// Random rnd = new Random(47);
		for (int i = 0; i < array.length; i++) {
			array[i] = rnd.nextInt(maxValue - minValue + 1) + minValue;
		}

		return array;
	}

	public static void printArray(int... array) {
		for (int element : array) {
			System.out.printf("%d\t", element);
		}
		System.out.println();
	}

	public static BigInteger summarizeArray(int startIndex, int endIndex, int... array) {
		BigInteger result = new BigInteger("0");

		for (int i = startIndex; i <= endIndex; i++) {
			result = result.add(new BigInteger(String.valueOf(array[i])));
		}

		return result;
	}

	/**
	 * @deprecated
	 */
	public static int[][] splitToArrays(int[] array, int count) {
		int size = array.length / count;
		int skipped = array.length - (size * count);
		int[][] arr = new int[count][];

		for (int i = 0; i < count - 1; i++) {
			arr[i] = new int[size];
		}
		arr[arr.length - 1] = skipped > 0 ? new int[size + skipped] : new int[size];

		int indexA = 0;
		int indexB = 0;
		for (int i = 0; i < array.length; i++) {
			try {
				arr[indexA][indexB++] = array[i];
			} catch (IndexOutOfBoundsException e) {
				indexA++;
				indexB = 0;
				arr[indexA][indexB++] = array[i];
			}
		}

		return arr;
	}

}
