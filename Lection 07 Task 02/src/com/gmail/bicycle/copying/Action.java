package com.gmail.bicycle.copying;

public class Action {
	private int value;
	private boolean turn;
	private boolean stop;

	public Action() {
		super();
	}

	public synchronized int getValue() {
		for (; turn == false;) {
			try {
				if (stop) {
					return -0;
				} else {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int temp = this.value;
		turn = false;
		notifyAll();
		System.out.println("accepted: " + this.value);
		return temp;
	}

	public synchronized void setValue(int value) {
		for (; turn == true;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.value = value;
		this.turn = true;
		System.out.println("send: " + this.value);
		notifyAll();
	}

	public boolean isStop() {
		return stop;
	}

	public synchronized void setStop(boolean stop) {
		this.stop = stop;
		notifyAll();
	}

	public boolean isTurn() {
		return turn;
	}

}
