package com.gmail.bicycle.bbt;

import java.util.ArrayDeque;
import java.util.Deque;

public class Actor implements Human {
	private String scenicName;

	public Actor() {
		super();
	}

	public Actor(String name) {
		super();
		this.scenicName = name;
	}

	public static Deque<Actor> initActors() {
		Actor sheldon = new Actor("Sheldon");
		Actor leonard = new Actor("Leonard");
		Actor volovitc = new Actor("Volovitc");
		Actor kutrapalli = new Actor("Kutrapalli");
		Actor penny = new Actor("Penny");

		Deque<Actor> actors = new ArrayDeque<>();
		actors.add(sheldon);
		actors.add(leonard);
		actors.add(volovitc);
		actors.add(kutrapalli);
		actors.add(penny);
		return actors;
	}
	
	@Override
	public String getName() {
		return scenicName;
	}

	@Override
	public String toString() {
		return scenicName;
	}

}
