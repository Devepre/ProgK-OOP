package com.gmail.bicycle.threads.thh;

import java.math.BigInteger;

import com.gmail.bicycle.threads.drivers.ArrayHandler;

public class SummarizeThread implements Runnable {
	private int numOfThreads;
	private int startIndex;
	private int endIndex;
	private int[] arrayToCount;
	private BigInteger calcResult = new BigInteger("0");
	private Thread[] threads;
	private SummarizeThread[] summarizeThreads;

	public SummarizeThread(int numOfThreads, int startIndex, int endIndex, int[] arrayToCount) {
		super();
		this.numOfThreads = numOfThreads;
		this.arrayToCount = arrayToCount;
		this.startIndex = startIndex;
		this.endIndex = endIndex;

		if (numOfThreads > arrayToCount.length || numOfThreads == arrayToCount.length) {
			throw new IllegalArgumentException("It's bad idea to use " + numOfThreads
					+ " threads for calculating array length: " + arrayToCount.length);
		}
	}

	@Override
	public void run() {
		if (numOfThreads > 1) {
			generate();
		} else {
			calculate();
		}
	}

	private void calculate() {
		BigInteger res = ArrayHandler.summarizeArray(startIndex, endIndex, arrayToCount);
		this.calcResult = res;
	}

	private void generate() {
		threads = new Thread[numOfThreads];
		summarizeThreads = new SummarizeThread[numOfThreads];

		int inputSize = arrayToCount.length;
		int sizeDivided = inputSize / numOfThreads;
		int sizeLast = inputSize - (sizeDivided * numOfThreads);
		sizeLast = sizeLast > 0 ? sizeDivided + sizeLast : sizeDivided;

		int start;
		int end;
		for (int i = 0; i < numOfThreads - 1; i++) {
			start = i * sizeDivided;
			end = i * sizeDivided + sizeDivided - 1;
			// System.out.println(start + "->" + end);
			SummarizeThread summarizeThread = new SummarizeThread(1, start, end, arrayToCount);
			summarizeThreads[i] = summarizeThread;
			Thread th = new Thread(summarizeThread);
			threads[i] = th;
		}
		start = inputSize - sizeLast;
		end = inputSize - 1;
		// System.out.println(start + "->" + end);
		SummarizeThread summarizeThread = new SummarizeThread(1, start, end, arrayToCount);
		summarizeThreads[summarizeThreads.length - 1] = summarizeThread;
		Thread th = new Thread(summarizeThread);
		threads[threads.length - 1] = th;
		startSubThreads();
		joinSubThreads();
		calculateSumFromThreads();
	}

	private void calculateSumFromThreads() {
		for (SummarizeThread sth : summarizeThreads) {
			calcResult = calcResult.add(sth.getCalcResult());
		}
	}

	private void startSubThreads() {
		for (Thread th : threads) {
			th.start();
		}
	}

	private void joinSubThreads() {
		for (Thread th : threads) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public BigInteger getCalcResult() {
		return calcResult;
	}

}
