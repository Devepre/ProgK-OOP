package com.gmail.bicycle.threads.drivers;

import java.text.SimpleDateFormat;

public class TimeWatcher {
	private long start;
	private long end;
	private static SimpleDateFormat sdf = new SimpleDateFormat("ss.SSS");

	public TimeWatcher() {
		super();
	}

	public void start() {
		this.start = System.currentTimeMillis();
	}

	public long stop() {
		this.end = System.currentTimeMillis();
		long spent = this.end - this.start;
		System.out.println("Time spent: " + sdf.format(spent));
		return spent;
	}

}
