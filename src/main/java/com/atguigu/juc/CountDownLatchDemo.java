package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
	public static void main(String[] args) throws Exception {
		CountDownLatch countDownLatch = new CountDownLatch(6);
		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				try {
					TimeUnit.SECONDS.sleep(4L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "\t 离开教室");
				countDownLatch.countDown();
			}, String.valueOf(i)).start();
		}

		/*
		 * try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */
		countDownLatch.await(2L, TimeUnit.SECONDS);
		System.out.println(Thread.currentThread().getName() + "\t 关门离开");
	}
}
