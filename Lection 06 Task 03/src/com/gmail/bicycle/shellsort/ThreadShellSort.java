package com.gmail.bicycle.shellsort;

import java.util.concurrent.Callable;

public class ThreadShellSort implements Callable<int[]> {
	private int[] array;
	
	public ThreadShellSort() {
		super();
	}

	public ThreadShellSort(int[] array) {
		super();
		this.array = array;
	}


	@Override
	public int[] call() throws Exception {
		ShellSort.sort(array);		
		return array;
	}	
	
}
