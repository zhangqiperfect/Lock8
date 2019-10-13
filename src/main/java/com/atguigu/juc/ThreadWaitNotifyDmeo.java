package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Airconditioner {
	private int number = 0;

	/*
	 * public synchronized void increase() throws InterruptedException { // 1.判断
	 * while (number != 0) { this.wait(); // A ,C } // 2.干活 number++;
	 * System.out.println(Thread.currentThread().getName() + number); // 3.通知
	 * this.notifyAll();
	 * 
	 * }
	 * 
	 * public synchronized void decrease() throws Exception { // 1.判断 while (number
	 * == 0) { this.wait(); // A ,C } // 2.干活 number--;
	 * System.out.println(Thread.currentThread().getName() + number); // 3.通知
	 * this.notifyAll();
	 * 
	 * }
	 */

	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();

	public void increase() throws InterruptedException {

		try {
			lock.lock();
			// 1.判断
			while (number != 0) {
				c1.await();

			}
			// 2.干活
			number++;
			System.out.println(Thread.currentThread().getName() + number);
			// 3.通知
			c1.signalAll();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void decrease() throws InterruptedException {

		try {
			lock.lock();
			// 1.判断
			while (number == 0) {
				c1.await();

			}
			// 2.干活
			number--;
			System.out.println(Thread.currentThread().getName() + number);
			// 3.通知
			c1.signalAll();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

}

public class ThreadWaitNotifyDmeo {
	public static void main(String[] args) {
		Airconditioner airconditioner = new Airconditioner();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					try {
						airconditioner.increase();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}, "A").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					try {
						airconditioner.decrease();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}, "B").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					try {
						airconditioner.increase();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}, "C").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					try {
						airconditioner.decrease();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}, "D").start();
	}
}
