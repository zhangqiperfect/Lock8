package com.atguigu.juc;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolDemo {
	public static void main(String[] args) {
		// Arrays Collections Executors(线程池相关工具类)
		// ExecutorService executorService = Executors.newFixedThreadPool(5);// 一池五线程
		// //ExecutorService executorService = Executors.newSingleThreadExecutor();
		// //ExecutorService executorService = Executors.newCachedThreadPool();//一池N线程
		//
		// try {
		// for (int i = 1; i <= 20; i++) { // 模拟20个客户来银行办理业务，提交请求
		// executorService.submit(() -> {
		// System.out.println(Thread.currentThread().getName() + "\t 办理业务" + new
		// Random().nextInt(100));
		// });
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// executorService.shutdown();
		// }

		ExecutorService executorService = new ThreadPoolExecutor(2, 5, 2L, TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
		try {
			for (int i = 1; i <= 9; i++) { // 模拟20个客户来银行办理业务，提交请求
				executorService.submit(() -> {
					System.out.println(Thread.currentThread().getName() + "\t 办理业务" + new Random().nextInt(100));
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}

	}
}
