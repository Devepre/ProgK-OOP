package com.gmail.bicycle;

public class TempJsonParser {
	private String text;
	private Object[] arr = new Object[10];
	private int n;

	public TempJsonParser() {
		super();
	}

	public void add(Object obj) {
		if (n == arr.length - 1) {
			this.resize();
		}
		arr[n++] = obj;
	}
	
	private void resize() {
		Object[] temp = new Object[(this.arr.length * 7) / 4];
		System.arraycopy(arr, 0, temp, 0, arr.length);
		arr = temp;
	}
	
	public class JsonIterator {
		private int pointer = 0;

		public boolean hasNext() {
			return pointer < TempJsonParser.this.n;
		}

		public Object next() {
			return TempJsonParser.this.arr[pointer++];
		}
	}

	public JsonIterator getIterator() {
		return new JsonIterator();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
}
