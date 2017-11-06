package com.gmail.bicycle.shellsort;

import java.util.Arrays;

import com.gmail.bicycle.threads.drivers.TimeWatcher;

public class Main {
	public static void main(String[] args) {
		int size = 100_000;
		int[] array = generateArray(size);
		int[] arrayB = Arrays.copyOf(array, array.length);
		
		System.out.println("Sorting using Threads");
		ParallelShellSort parallelShellSort = new ParallelShellSort(array);
		Thread th = new Thread(parallelShellSort);
		TimeWatcher timeWatcher = new TimeWatcher();		
		timeWatcher.start();		
		th.start();
		try {
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timeWatcher.stop();
		
		System.out.println("Sorting using one Thread");
		timeWatcher.start();
		ShellSort.sort(arrayB);
		timeWatcher.stop();
		
		System.out.println("The end");
		
	}
	
	private static int[] generateArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = size - i;
		}	
		return array;
	}
	
	private static void printArray(int[] array) {
		for(int i: array) {
			System.out.print(i + " ");
		}
	}

}
