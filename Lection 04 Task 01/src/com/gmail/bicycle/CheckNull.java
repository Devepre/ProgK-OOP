package com.gmail.bicycle;

public interface CheckNull {
	public int NOT_NULL = 47;
	
	public static int checkNull(Object a, Object b) {
		if (a == null && b != null) {
			return -1;
		} else if (a!= null && b == null) {
			return 1;
		} else if (a == null && b == null) {
			return 0;
		}
		return NOT_NULL;
	}

}
