package com.atguigu.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
	public static void main(String[] args) throws Exception {
		BlockingQueue blockingQueue =new ArrayBlockingQueue<>(3);
	/*	System.out.println(blockingQueue.add("a"));
		System.out.println(blockingQueue.add("c"));
		System.out.println(blockingQueue.add("d"));
		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.element());*/
		
		/*System.out.println(blockingQueue.offer("a"));
		System.out.println(blockingQueue.offer("a"));
		System.out.println(blockingQueue.offer("a"));
		System.out.println(blockingQueue.offer("a"));
		System.out.println(blockingQueue.poll());
		System.out.println(blockingQueue.poll());
		System.out.println(blockingQueue.poll());
		System.out.println(blockingQueue.poll());*/
		
		blockingQueue.put("a");
		blockingQueue.put("a");
		blockingQueue.put("a");
		System.out.println(blockingQueue.take());
		System.out.println(blockingQueue.take());
		System.out.println(blockingQueue.take());
		System.out.println(blockingQueue.take());
	}
}
