package com.gmail.bicycle.shellsort;

import java.util.ArrayList;

public class ShellSort {

	public static void sort(int[] array) {
		int length = array.length;
		int j;
		ArrayList<Integer> valuesD = generateGapSequences(array);

		for (int value : valuesD) {
			for (int i = value; i < length; i++) {
				int swap = array[i];
				for (j = i; j >= value && array[j - value] > swap; j -= value) {
					array[j] = array[j - value];
				}
				array[j] = swap;
			}
		}
	}
	
	public static ArrayList<Integer> generateGapSequences(int[] array) {
		ArrayList<Integer> gapSequences = new ArrayList<Integer>();
		int length = array.length;
		int gap;
		for (int i = 1; (gap = (int)Math.round((length / Math.pow(2, i)))) > 1 ; i++) {
			gapSequences.add(gap);
		}
		if (gapSequences.get(gapSequences.size() - 1) != 1) {
			gapSequences.add(1);
		}
		
		return gapSequences;		
	}
}
