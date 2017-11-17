package com.gmail.bicycle.bbt;

import java.util.ArrayDeque;
import java.util.Deque;

public class ColaMut<T extends Human> {
	private Deque<T> que = new ArrayDeque<>();

	public ColaMut() {
		super();
	}

	public ColaMut(Deque<T> que) {
		super();
		this.que = que;
	}

	public void giveCola(int number) {
		for (int i = 0; i < number; i++) {
			this.giveCola();
		}
	}

	public void giveCola() {
		T drinked = que.pop();
		que.add(drinked);
		que.add(drinked);
	}

	public Deque<T> getQue() {
		return que;
	}

	public void setQue(Deque<T> que) {
		this.que = que;
	}

	@Override
	public String toString() {
		return "Another one usual ColaMut";
	}

}
