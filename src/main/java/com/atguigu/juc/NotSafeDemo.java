package com.atguigu.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotSafeDemo {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		// List<String> list = Collections.synchronizedList(new ArrayList<>());
		// List<String> list = new Vector<>();
		// List<String> list = new CopyOnWriteArrayList<>();
		/*
		 * list.add("a"); list.add("b"); list.add("c"); System.out.println(list);
		 */
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 6));
				System.out.println(list);
			}, String.valueOf(i)).start();
		}
	}
}
