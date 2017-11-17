package com.gmail.bicycle.bbt;

import java.util.Deque;

public class Main {
	public static final int MAX_COUNT = 20;

	public static void main(String[] args) {
		Deque<Actor> actors = Actor.initActors();
		ColaMut<Actor> colaMut = new ColaMut<>(actors);
		
		int count = (int) (Math.random() * MAX_COUNT);
		System.out.println(count + " drinks utilized");
		for (int i = 0; i < count; i++) {
			colaMut.giveCola();
		}
		
		System.out.println(colaMut.getQue());
	}

}
