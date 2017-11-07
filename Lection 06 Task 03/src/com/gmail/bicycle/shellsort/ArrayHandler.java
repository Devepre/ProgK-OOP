package com.gmail.bicycle.shellsort;

public class ArrayHandler {

	public ArrayHandler() {
		super();
	}

	public static int[] generateArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = size - i;
		}
		return array;
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
	}

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

	public static int[] mergeArrays(int[][] array) {
		int dimensions = array.length;
		int[] resultArray = new int[array[0].length];		
		System.arraycopy(array[0], 0, resultArray, 0, resultArray.length);

		for (int i = 1; i < dimensions; i++) {
			resultArray = mergeArrays(resultArray, array[i]);
		}

		return resultArray;
	}

	public static int[] mergeArrays(int[] arrayA, int[] arrayB) {
		int[] resultArray = new int[arrayA.length + arrayB.length];
		int indexA = 0;
		int indexB = 0;

		for (int index = 0; index < resultArray.length; index++) {
			resultArray[index] = arrayA[indexA] < arrayB[indexB] ? arrayA[indexA++] : arrayB[indexB++];
			if (indexA == arrayA.length) {
				System.arraycopy(arrayB, indexB, resultArray, ++index, arrayB.length - indexB);
				break;
			}
			if (indexB == arrayB.length) {
				System.arraycopy(arrayA, indexA, resultArray, ++index, arrayA.length - indexA);
				break;
			}
		}
		return resultArray;
	}

}
