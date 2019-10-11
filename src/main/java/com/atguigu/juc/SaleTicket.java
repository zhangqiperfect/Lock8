package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
	/*
	 * private int num = 30;
	 * 
	 * public synchronized void sale() { if (num > 0) {
	 * System.out.println(Thread.currentThread().getName() + "正在卖第" + (num--) +
	 * "张票，还剩" + num + "张票"); } }
	 */
	private int num = 30;
	Lock lock = new ReentrantLock();

	public void sale() {
		lock.lock();
		try {
			if (num > 0) {
				System.out.println(Thread.currentThread().getName() + "\t 正在卖第：" + (num--) + "\t 还剩:" + num + "张");
			}
		} catch (Exception e) {
			e.printStackTrace();
			lock.unlock();
		}
	}
}

public class SaleTicket {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(() -> {
			for (int i = 1; i <= 50; i++)
				ticket.sale();
		}, "a").start();
		new Thread(() -> {
			for (int i = 1; i <= 50; i++)
				ticket.sale();
		}, "c").start();
		new Thread(() -> {
			for (int i = 1; i <= 50; i++)
				ticket.sale();
		}, "d").start();

		/*
		 * new Thread(new Runnable() {
		 * 
		 * public void run() { for (int i = 1; i <= 50; i++) { ticket.sale(); } } },
		 * "a").start();
		 */

		/*
		 * new Thread(new Runnable() {
		 * 
		 * public void run() { for (int i = 1; i <= 50; i++) { ticket.sale(); } } },
		 * "b").start();
		 * 
		 * new Thread(new Runnable() {
		 * 
		 * public void run() { for (int i = 1; i <= 50; i++) { ticket.sale(); } } },
		 * "c").start();
		 */
	}
}
