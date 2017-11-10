package com.gmail.bicycle.copying;

public class Main {

	public static void main(String[] args) {
		Action action = new Action();
		InputThread inputThread = new InputThread(action);
		ProgressThread progressThread = new ProgressThread(action);
		
		Thread threadInput = new Thread(inputThread);
		Thread threadProgress = new Thread(progressThread);
		threadInput.start();
		threadProgress.start();
		
		System.out.println("The end");
	}

}
