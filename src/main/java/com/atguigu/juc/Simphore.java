package com.atguigu.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Simphore {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);// 模拟三个停车位
		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				boolean flag = false;
				try {
					//semaphore.acquire();
					semaphore.tryAcquire(40L, TimeUnit.MILLISECONDS);
					flag = true;
					System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
					// 暂停三秒线程
					TimeUnit.SECONDS.sleep(new Random().nextInt(5));
					System.out.println(Thread.currentThread().getName() + "\t 离开车位");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if (flag) {
						semaphore.release();
					}
				}
			}, String.valueOf(i)).start();
		}
	}
}
