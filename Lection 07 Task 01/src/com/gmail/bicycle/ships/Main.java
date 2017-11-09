package com.gmail.bicycle.ships;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {
	private final static int NUM_OF_THREADS = 2;
	private static int shipNumber = 1;
	private static long timePeriodMs = 500;

	private static Ship shipOne;
	private static Ship shipTwo;
	private static Ship shipThree;
	private static Harbor shipHarbor;

	public static void main(String[] args) {
		initializeInfrastructure();

		List<Thread> shipThreads = new ArrayList<Thread>();
		Thread thShipOne = new Thread(shipOne);
		shipThreads.add(thShipOne);
		Thread thShipTwo = new Thread(shipTwo);
		shipThreads.add(thShipTwo);
		Thread thShipThree = new Thread(shipThree);
		shipThreads.add(thShipThree);

		Thread thShipHarbor = new Thread(shipHarbor, "Harbor Pearl");
		thShipHarbor.setDaemon(true);
		thShipHarbor.start();

		final class ShipThreadFactory implements ThreadFactory {
			public Thread newThread(Runnable r) {
				return new Thread(r, "Ship ID:" + Main.shipNumber++);
			}
		}

		ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS, new ShipThreadFactory());
		for (Thread thread : shipThreads) {
			executorService.submit(thread);
		}
		executorService.shutdown();

	}

	private static void initializeInfrastructure() {
		List<String> harborStorage = new ArrayList<String>();
		harborStorage.add("Bananas");
		harborStorage.add("Slaves");

		List<String> shipmentOne = new ArrayList<String>();
		shipmentOne.add("Java");
		shipmentOne.add("C");
		shipmentOne.add("Cobol");
		shipmentOne.add("Perl");
		shipmentOne.add("Pascal");
		shipmentOne.add("Lisp");
		shipmentOne.add("Clojure");
		shipmentOne.add("JavaScript");
		shipmentOne.add("Basic");
		shipmentOne.add("C++");

		List<String> shipmentTwo = new ArrayList<String>();
		shipmentTwo.add("Motherboards");
		shipmentTwo.add("CPUs");
		shipmentTwo.add("HDDs");
		shipmentTwo.add("DVDs");
		shipmentTwo.add("FANs");
		shipmentTwo.add("Keyboards");
		shipmentTwo.add("Mouses");
		shipmentTwo.add("Monitors");
		shipmentTwo.add("Laptops");
		shipmentTwo.add("Speakers");

		List<String> shipmentThree = new ArrayList<String>();
		shipmentThree.add("Apple");
		shipmentThree.add("Banana");
		shipmentThree.add("Carrot");
		shipmentThree.add("Lemon");
		shipmentThree.add("Elderberry");
		shipmentThree.add("Fig");
		shipmentThree.add("Grape");
		shipmentThree.add("Guava");
		shipmentThree.add("Kiwi");
		shipmentThree.add("Strawberry");

		Exchanger<String> exChanger = new Exchanger<>();
		shipOne = new Ship(exChanger, shipmentOne, timePeriodMs);
		shipTwo = new Ship(exChanger, shipmentTwo, timePeriodMs);
		shipThree = new Ship(exChanger, shipmentThree, timePeriodMs);
		shipHarbor = new Harbor(exChanger, harborStorage);
	}

}
