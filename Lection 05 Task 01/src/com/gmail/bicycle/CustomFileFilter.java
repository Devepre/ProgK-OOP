package com.gmail.bicycle;

import java.io.File;
import java.io.FileFilter;

public class CustomFileFilter implements FileFilter {
	public static final String FILTER_DEFAULT = "txt";
	private String[] filters;

	public CustomFileFilter() {
		super();
		filters = new String[] { FILTER_DEFAULT };
	}

	public CustomFileFilter(String... filters) {
		super();
		if (filters != null) {
			this.filters = filters;
		} else {
			throw new IllegalArgumentException("Filters can't be null");
		}
	}

	@Override
	public boolean accept(File file) {
		if (file.isFile()) {
			String fileName = file.getName();
			String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
			return belongsToFilter(fileExtension);
		} else {
			return false;
		}

	}

	protected boolean belongsToFilter(String fileExtension) {
		for (String string : filters) {
			if (string.equals(fileExtension)) {
				return true;
			}
		}
		return false;
	}

	public String[] getFilters() {
		return filters;
	}

	public void setFilters(String[] filters) {
		this.filters = filters;
	}

}
