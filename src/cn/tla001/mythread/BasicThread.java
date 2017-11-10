package cn.tla001.mythread;

//创建线程 方式一：继承Thread类
class MyThread1 extends Thread {
	private String name;

	public MyThread1(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(this.name + " is running");
	}
}

// 创建线程 方式二：实现Runnable接口
class MyThread2 implements Runnable {
	private String name;

	public MyThread2(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(this.name + " is running");
	}
}

public class BasicThread {
	public static void main(String[] args) {
		Thread thread1 = new MyThread1("thread-1");
		thread1.start();

		Thread thread2 = new Thread(new MyThread2("thread-2"));
		thread2.start();
		System.out.println("main out");
	}
}
