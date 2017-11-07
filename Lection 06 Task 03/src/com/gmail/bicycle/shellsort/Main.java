package com.gmail.bicycle.shellsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.gmail.bicycle.threads.drivers.TimeWatcher;

public class Main {
	private static final int NUM_OF_BLOCKS = 4;
	private static final int SIZE = 15_000_000;

	public static void main(String[] args) {
		int[] arrayA = ArrayHandler.generateArray(SIZE);
		int[] arrayB = Arrays.copyOf(arrayA, arrayA.length);

		TimeWatcher timeWatcher = new TimeWatcher();
	
//		old implementation using ShellSort.java class
//		System.out.println("Sorting using Threads");
//		ParallelShellSort parallelShellSort = new ParallelShellSort(arrayA);
//		Thread th = new Thread(parallelShellSort);
//		timeWatcher.start();
//		th.start();
//		try {
//			th.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		timeWatcher.stop();

		System.out.println("Sorting using one Thread");
		timeWatcher.start();
		ShellSort.sort(arrayB);
		timeWatcher.stop();

		System.out.println();
		System.out.println("Sorting parallel Threads ver.2");
		timeWatcher.start();
		int[] arrayS = multiSortArray(arrayA);
		timeWatcher.stop();
		
		// ArrayHandler.printArray(arrayS);
		System.out.println("The end");
	}

	public static int[] multiSortArray(int[] array) {
		System.out.print("Splitting array to the parts: ");
		TimeWatcher timeWatcher = new TimeWatcher();
		timeWatcher.start();
		int[][] arraySplitted = ArrayHandler.splitToArrays(array, NUM_OF_BLOCKS);
		int[][] arraySorted = new int[arraySplitted.length][];
		timeWatcher.stop();

		ArrayList<Future<int[]>> result = new ArrayList<>();
//		using FutureTask interface
//		for (int[] arr : arraySplitted) {
//			FutureTask<int[]> res = new FutureTask<>(new ThreadShellSort(arr));
//			result.add(res);
//			Thread thread = new Thread(res);
//			thread.start();
//		}		
		ExecutorService exSer = Executors.newFixedThreadPool(NUM_OF_BLOCKS);
		for (int[] arr : arraySplitted) {
			result.add(exSer.submit(new ThreadShellSort(arr)));
			}
		
		int index = 0;
		for (Future<int[]> future : result) {		
			try {
				arraySorted[index++] = future.get();				
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		exSer.shutdown();

		System.out.print("Merging arrays back: ");
		timeWatcher.start();
		int[] arrayResult = ArrayHandler.mergeArrays(arraySorted);
		timeWatcher.stop();

		return arrayResult;
	}

}
