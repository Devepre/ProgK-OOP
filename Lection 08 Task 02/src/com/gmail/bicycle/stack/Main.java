package com.gmail.bicycle.stack;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {

		try {
			Stack stack = new Stack();
			stack.setSelfPrinted(true);
			stack.push(5);
			stack.push(7);

			Object obj = stack.pop();
			System.out.println("<Pop> result: " + obj);

			stack.push(new ArrayList<Main>(3).add(new Main()));
			stack.push("World");

			Object oo = stack.get();
			System.out.println("<Get> Object recieved: " + oo);
			stack.push(0.3);

			BlackList blackList = new BlackList();
			blackList.add(stack.get().getClass());
			System.out.println(blackList);

			stack.setBlackList(blackList);
			stack.setBlackList(null);
			stack.push(Math.E);
			stack.setBlackList(blackList);
			stack.push(Math.PI);

		} catch (StackCapaictyException | IllegalArgumentException e) {
			e.printStackTrace();
		}

		System.out.println("the end");
	}

}
