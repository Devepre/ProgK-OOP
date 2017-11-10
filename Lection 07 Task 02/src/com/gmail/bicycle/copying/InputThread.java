package com.gmail.bicycle.copying;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputThread implements Runnable {
	private Action action;
	private File original;
	private int bufferSize = 4000 * 4000;

	public InputThread(Action action, File original, int bufferSize) {
		super();
		this.action = action;
		this.original = original;
		this.bufferSize = bufferSize;
	}

	@Override
	public void run() {
		copyFile();
		action.setStop(true);
	}

	private void copyFile() {
		if (original.isDirectory()) {
			throw new IllegalArgumentException("Can copy only File to Directory");
		}

		if (!original.exists()) {
			throw new IllegalArgumentException("Directory of File doesn't exist");
		}

		try (InputStream fis = new FileInputStream(original)) {
			byte[] dataBuffer = new byte[bufferSize];
			int count = 0;
			while ((count = fis.read(dataBuffer)) > 0) {
				action.setBuffer(dataBuffer, count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
