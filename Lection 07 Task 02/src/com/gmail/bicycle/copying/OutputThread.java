package com.gmail.bicycle.copying;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputThread implements Runnable {
	private Action action;
	private Action actionProgress;
	private File destination;

	public OutputThread(Action action, Action progress, File destination) {
		super();
		this.action = action;
		this.actionProgress = progress;
		this.destination = destination;
	}

	@Override
	public void run() {
		if (destination.exists()) {
			destination.delete();
		}
		byte[] data = null;
		for (; action.isTurn() || !action.isStop();) {
			data = action.getBuffer();
			if (data != null) {
				try (FileOutputStream fos = new FileOutputStream(destination, true)) {
					fos.write(data, 0, data.length);

					actionProgress.setCounter(data.length);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		actionProgress.setStop(true);

	}

}
