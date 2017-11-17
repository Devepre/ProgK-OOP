package com.gmail.bicycle.bbt;

import java.util.Deque;

public class Main {
	public static final int MAX_COUNT = 20;

	public static void main(String[] args) {
		Deque<Actor> actors = Actor.initActors();
		ColaMut<Actor> colaMut = new ColaMut<>(actors);

		int number = (int) (Math.random() * MAX_COUNT);
		System.out.println(number + " drinks utilized");
		colaMut.giveCola(number);

		System.out.println(colaMut.getQue());
	}

}
