package com.atguigu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*class Mythread2 implements Runnable {

	@Override
	public void run() {

	}
}
*/
/*区别：
 * 1.实现接口不同
 * 2.有无返回值
 * 3.是否抛异常
 * */
/*class MyThread implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName()+"\t ********come in call ");
		return "0615";
	}

}*/

public class CallDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*
		 * FutureTask<String> futureTask = new FutureTask<>(new MyThread()); new
		 * Thread(futureTask, "AA").start(); String result = futureTask.get();
		 * System.out.println(result);
		 */
		// System.out.println(Thread.currentThread().getName()+"\t **********主线程");
		FutureTask<String> futureTask = new FutureTask<>(() -> {
			System.out.println(Thread.currentThread().getName() + "\t ********come in call ");
			return "0615";
		});
		new Thread(futureTask, "AA").start();
		System.out.println(Thread.currentThread().getName() + "\t **********主线程");
		// get方法一般放在最后一行
		String result = futureTask.get();
		System.out.println(result);
	}
}
