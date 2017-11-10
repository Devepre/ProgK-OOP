package com.gmail.bicycle.copying;

public class Action {
	private byte[] buffer;
	private long count;

	private boolean turn;
	private boolean stop;

	public Action() {
		super();
	}

	public synchronized byte[] getBuffer() {
		for (; turn == false ;) {
			try {
				if (stop) {
					return null;
				} else {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		turn = false;
		notifyAll();
		return this.buffer;
	}

	public synchronized void setBuffer(byte[] value, int count) {
		for (; turn == true;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.buffer = new byte[count];
		System.arraycopy(value, 0, this.buffer, 0, count);
		this.turn = true;
		notifyAll();
	}

	public synchronized void setCounter(int count) {
		for (; turn == true;) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.count = count;
		this.turn = true;
		notifyAll();
	}

	public synchronized long getCounter() {
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

		long temp = this.count;
		turn = false;
		notifyAll();
		return temp;
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
