package com.atguigu.juc;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
/**笔记
 * 写时复制
 CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行Copy，
 复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加完元素之后，
 再将原容器的引用指向新的容器 setArray(newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读，
 而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
 public boolean add(E e)
 {
 	final ReentrantLock lock = this.lock;

 	lock.lock();
	
	 try{
	 Object[] ele ments = getArray();
	 int len = elements.length;
	 Object[] newElements = Arrays.copyOf(elements, len + 1);
	 newElements[len] = e;
	 setArray(newElements);
	 return true;
   }
	 finally {
	 lock.unlock();
   }
 }
 */
public class NotSafeDemo {
	public static void main(String[] args) {
		/*
		 * List<String> list = new ArrayList<>(); List<String> list =
		 * Collections.synchronizedList(new ArrayList<>()); List<String> list = new
		 * Vector<>(); List<String> list = new CopyOnWriteArrayList<>();
		 * 
		 * for (int i = 1; i <= 3; i++) { new Thread(() -> {
		 * list.add(UUID.randomUUID().toString().substring(0, 6));
		 * System.out.println(list); }, String.valueOf(i)).start(); }
		 */

		/*
		 * Set<String> set = new HashSet<>(); Set<String > set =
		 * Collections.synchronizedSet(new HashSet<>()); Set<String > set =new
		 * CopyOnWriteArraySet<String>(); for (int i = 1; i <= 30; i++) { new Thread(new
		 * Runnable() {
		 * 
		 * @Override public void run() {
		 * set.add(UUID.randomUUID().toString().substring(0, 6));
		 * System.out.println(set);
		 * 
		 * } }, String.valueOf(i)).start(); }
		 */

		// Map<String, String> map = new HashMap<String, String>();
//		Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
		Map<String, String> map = new ConcurrentHashMap<>();

		for (int i = 1; i <= 30; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 6));
					System.out.println(map);
				}
			}, String.valueOf(i)).start();

		}
	}
}
