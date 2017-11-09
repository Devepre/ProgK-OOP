package com.gmail.bicycle.ships;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Ship implements Runnable {
	private List<String> shipment;
	private Exchanger<String> exCahnger;	
	private long time;

	public Ship() {
		super();
	}

	public Ship(Exchanger<String> exCahnger, List shipment, long time) {
		super();
		this.exCahnger = exCahnger;		
		this.shipment = shipment;
		this.time = time;
	}

	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:S");
		Thread currentThread = Thread.currentThread();
		System.out.println(sdf.format(System.currentTimeMillis())  + "\t" + currentThread.getName() + "\t| shipment available: " + this.shipment);
		
		for (; !shipment.isEmpty();) {
			try {
				Thread.sleep(time);
				String item = this.shipment.get(this.shipment.size() - 1);
				exCahnger.exchange(item);
				this.shipment.remove(item);
				System.out.println(sdf.format(System.currentTimeMillis())  + "\t" + currentThread.getName() + "\t| after loading to harbor: " + this.shipment);
				
				if (currentThread.isInterrupted()) {
					System.out.println(sdf.format(System.currentTimeMillis())  + "t" + currentThread.getName() + "\t| going under the water! Current items are still on the board: " + this.shipment);
					return;
				}
			} catch ( InterruptedException e) {
				e.printStackTrace();
			}
		}	
		System.out.println(sdf.format(System.currentTimeMillis()) + "\t" + currentThread.getName() + "\t| leaving the harbor");
	}

}
