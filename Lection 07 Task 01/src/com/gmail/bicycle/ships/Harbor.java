package com.gmail.bicycle.ships;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Harbor implements Runnable {
	private List<String> storage;
	private Exchanger<String> exChanger;

	public Harbor() {
		super();
	}

	public Harbor(Exchanger<String> exChanger, List storage) {
		super();
		this.exChanger = exChanger;
		this.storage = storage;
	}

	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:S");
		Thread currentThread = Thread.currentThread();
		System.out.println(sdf.format(System.currentTimeMillis())  + "\t" + currentThread.getName() + "\t| current storage: " + this.storage);

		for (; !Thread.currentThread().isInterrupted();) {
			try {
				this.storage.add(exChanger.exchange("thank you for using our port"));
				System.out.println(sdf.format(System.currentTimeMillis())  + "\t" + currentThread.getName() + "\t| after loading: " + this.storage);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sdf.format(System.currentTimeMillis())  + "\tHarbor was attacked by pirates!");
	}

}
