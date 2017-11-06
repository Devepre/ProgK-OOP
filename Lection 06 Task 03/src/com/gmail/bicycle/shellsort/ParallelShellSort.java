package com.gmail.bicycle.shellsort;

import java.util.ArrayList;

public class ParallelShellSort implements Runnable {
	private int[] array;
	private boolean firstIteration;
	private int value;

	public ParallelShellSort() {
		super();
		this.firstIteration = true;
	}

	public ParallelShellSort(int[] array) {
		super();
		this.array = array;
		this.firstIteration = true;		
	}

	public ParallelShellSort(int value, int[] array) {
		super();
		this.value = value;
		this.array = array;
	}
	
	@Override
	public void run() {
		if (firstIteration) {
			beginIteration();
		} else {
			subSort(value);
		}
	}

	public void beginIteration() {
		this.firstIteration = false;		
		
		ArrayList<Integer> valuesD = generateGapSequences();
		ArrayList<Thread> threads = new ArrayList<Thread>(valuesD.size());
		for (int value : valuesD) {
			ParallelShellSort parallelShellSort = new ParallelShellSort(value, array);
			Thread th = new Thread(parallelShellSort);
			threads.add(th);
			th.start();
		}
		
		joinAll(threads);

	}

	public void subSort(int value) {
		int j;
		for (int i = value; i < array.length; i++) {
			int swap = array[i];
			for (j = i; j >= value && array[j - value] > swap; j -= value) {
				array[j] = array[j - value];
			}
			array[j] = swap;
		}
	}

	public ArrayList<Integer> generateGapSequences() {
		ArrayList<Integer> gapSequences = new ArrayList<Integer>();
//		ArrayList<Integer> gapSequences = new ArrayList<Integer>();
//		gapSequences.add(50000);
//		gapSequences.add(6250);
//		gapSequences.add(1);
		
		int length = array.length;
		int gap;
		for (int i = 1; (gap = (int) Math.round((length / Math.pow(2, i)))) > 1; i++) {
			gapSequences.add(gap);
		}
		if (gapSequences.get(gapSequences.size() - 1) != 1) {
			gapSequences.add(1);
		}

		return gapSequences;
	}
	
	private static void joinAll(ArrayList<Thread> threads) {
		for (Thread th : threads) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
