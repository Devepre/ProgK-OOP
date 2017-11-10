package com.gmail.bicycle.copying;

public class InputThread implements Runnable {
	private Action action;

	public InputThread(Action action) {
		super();
		this.action = action;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			action.setValue(i);
		}
		action.setStop(true);

	}

}
