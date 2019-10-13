package com.atguigu.juc;

import java.util.concurrent.TimeUnit;
/**
*
* 题目：多线程8锁
* 1  标准访问，请问先打印邮件还是短信？
* 2  邮件新增暂停4秒钟的方法，请问先打印邮件还是短信？
* 3  新增普通的hello方法，请问先打印邮件还是hello
* 4  有两部手机，请问先打印邮件还是短信？
* 5  两个静态同步方法，同一部手机，请问先打印邮件还是短信？
* 6  两个静态同步方法，2部手机，请问先打印邮件还是短信？
* 7  1个静态同步方法,1个普通同步方法，1部手机，请问先打印邮件还是短信？
* 8  1个静态同步方法,1个普通同步方法，2部手机，请问先打印邮件还是短信？

笔记
*  一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，
*  其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
*  锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
*
*   加个普通方法后发现和同步锁无关
*
*   换成两个对象后，不是同一把锁了，情况立刻变化。
*
*     都换成静态同步方法后，情况又变化
*
*
*  都换成静态同步方法后，情况又变化
*  若是普通同步方法，new     this,   具体的一部部手机,所有的普通同步方法用的都是同一把锁——实例对象本身，
*  若是静态同步方法，static  Class ，唯一的一个模板
*
*
*  synchronized是实现同步的基础：Java中的每一个对象都可以作为锁。
*  具体表现为以下3种形式。
*  对于普通同步方法，锁是当前实例对象。它等同于  对于同步方法块，锁是Synchonized括号里配置的对象。
*  对于静态同步方法，锁是当前类的Class对象本身，
*
*   *  当一个线程试图访问同步代码时它首先必须得到锁，退出或抛出异常时必须释放锁。
*  *
*  *  所有的普通同步方法用的都是同一把锁——实例对象本身，，就是new出来的具体实例对象本身
*  *  也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他普通同步方法必须等待获取锁的方法释放锁后才能获取锁，
*  *  可是别的实例对象的普通同步方法因为跟该实例对象的普通同步方法用的是不同的锁，所以不用等待该实例对象已获取锁的普通
*  *  同步方法释放锁就可以获取他们自己的锁。
*  *
*  *  所有的静态同步方法用的也是同一把锁——类对象本身，就是我们说过的唯一模板Class
*  *  具体实例对象this和唯一模板Class，这两把锁是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竞态条件的。
*  *  但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
*
*
*  Car car1 = new Car();
*  Car car2 = new Car();
*  Car car3 = new Car();
*
*/
class Phone {
	public static synchronized void sendEmail() throws InterruptedException {
		TimeUnit.SECONDS.sleep(4);
		System.out.println("*******sendEmail");
	}

	public synchronized void sendMsg() {
		System.out.println("*******sendMsg");
	}

	public void hello() {
		System.out.println("******sayhello");
	}
}

public class Lock8 {
	public static void main(String[] args) throws InterruptedException {
		Phone phone = new Phone();
		Phone phone2 = new Phone();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					phone.sendEmail();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "A").start();

		Thread.sleep(100);

		new Thread(new Runnable() {

			@Override
			public void run() {
//				 phone.sendMsg();
				phone2.sendMsg();
				// phone.hello();
			}
		}, "B").start();

	}
}
