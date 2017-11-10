package com.gmail.bicycle.copying;

public class ProgressThread implements Runnable {
	private Action action;

	public ProgressThread(Action action) {
		super();
		this.action = action;
	}

	@Override
	public void run() {
		int data = 0;
		for (; action.isTurn() || !action.isStop();) {
			data = action.getValue();
		}
	}

}
