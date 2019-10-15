package com.atguigu.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDmeo {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{System.out.println("集齐七颗龙珠召唤神龙");});
		for (int i = 1; i <=7; i++) {
			int finalI=i;
			new Thread(()->{
				System.out.println("收集到第"+finalI+"颗龙珠");
				try {
					cyclicBarrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			},String.valueOf(i)).start();
			
		}
	}
}
