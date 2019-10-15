package com.atguigu.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Mycach {
	private volatile Map<String, String> map = new HashMap<String, String>();
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public void put(String key, String value) {
		readWriteLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t 写入开始");
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + "\t 写入结束");
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	public void get(String key) {
		readWriteLock.readLock().lock();
		try {
			String result = null;
			System.out.println(Thread.currentThread().getName() + "\t 读开始");
			result = map.get(key);
			System.out.println(Thread.currentThread().getName() + "\t 读结束result:" + result);
		} finally {
			readWriteLock.readLock().unlock();
		}
	}

	/*
	 * private volatile Map<String, String> map = new HashMap<String, String>();
	 * 
	 * Lock lock = new ReentrantLock();
	 * 
	 * public void put(String key, String value) { lock.lock(); try {
	 * System.out.println(Thread.currentThread().getName() + "\t 写入开始");
	 * map.put(key, value); System.out.println(Thread.currentThread().getName() +
	 * "\t 写入结束"); } finally { lock.unlock(); } }
	 * 
	 * public void get(String key) { lock.lock(); try { String result = null;
	 * System.out.println(Thread.currentThread().getName() + "\t 读开始"); result =
	 * map.get(key); System.out.println(Thread.currentThread().getName() +
	 * "\t 读结束result:" + result); } finally { lock.unlock(); } }
	 */
}

public class ReadWriteLockDemo {
	public static void main(String[] args) {
		Mycach mycach = new Mycach();
		for (int i = 1; i <= 10; i++) {
			int finalI = i;
			new Thread(() -> {
				mycach.put(finalI + "", finalI + "");
			}, String.valueOf(i)).start();
		}
		for (int i = 1; i <= 10; i++) {
			int finalI = i;
			new Thread(() -> {
				mycach.get(finalI + "");
			}, String.valueOf(i)).start();
		}
	}
}
