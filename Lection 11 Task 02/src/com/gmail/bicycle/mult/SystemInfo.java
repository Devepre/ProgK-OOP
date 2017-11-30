package com.gmail.bicycle.mult;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SystemInfo {
	private Map<String, String> info = new HashMap<>();
	
	public SystemInfo() {
		super();
		getServerInfo();
	}

	private final void getServerInfo() {
		Runtime rnt = Runtime.getRuntime();
		this.info.put("Available processors:", String.valueOf(rnt.availableProcessors()));
		this.info.put("Total memory", String.valueOf(rnt.totalMemory()));
		this.info.put("Free memory", String.valueOf(rnt.freeMemory()));

		File[] roots = File.listRoots();
		for (File root : roots) {
			this.info.put("Total space (bytes): " + root.getAbsolutePath(), String.valueOf(root.getTotalSpace()));
			this.info.put("Free space (bytes): " + root.getAbsolutePath(), String.valueOf(root.getFreeSpace()));
		}

		try {
			this.info.put("System Info", SystemInfo.getSystemInfo());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void printInfo() {
		this.info.forEach((key, value) -> System.out.println(key + " -> " + value));
	}

	private static String getSystemInfo() throws IOException {
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec("systeminfo");
		BufferedReader systemInformationReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		StringBuilder sb = new StringBuilder("OS info" + System.lineSeparator());
		
		String line;
		while ((line = systemInformationReader.readLine()) != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
		}

		return sb.toString().trim();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		Set<String> keys = this.info.keySet();
		for (String key : keys) {
			sb.append(key + " -> " + this.info.get(key));
			sb.append(System.lineSeparator());
		}

		return sb.toString().trim();
	}

}
