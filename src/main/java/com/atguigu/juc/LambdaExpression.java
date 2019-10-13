package com.atguigu.juc;

import java.util.ArrayList;
import java.util.Vector;

@FunctionalInterface
interface Foo {
	// public void sayHello();
	public int add(int x, int y);

	default int mul(int x, int y) {
		return x * y;
	}

	static int dev(int x, int y) {
		return x / y;
	}
}

public class LambdaExpression {
	public static void main(String[] args) {
		/*
		 * Foo foo =new Foo() {
		 * 
		 * @Override public void sayHello() { System.out.println("***********hello"); }
		 * }; foo.sayHello();
		 */
		/*
		 * Foo foo = () -> {System.out.println("************hello");}; foo.sayHello();
		 */
		Foo foo = (int x, int y) -> {
			System.out.println("come in add method");
			return x + y;
		};
		System.out.println(foo.add(3, 5));
		System.out.println(foo.mul(3, 5));
		System.out.println(Foo.dev(3, 3));
	}
}
