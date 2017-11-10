package cn.tla001.mythread;

class MyThread3 implements Runnable {
	private String name;

	public MyThread3(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// 静态方法，获取线程名称
		System.out.println(Thread.currentThread().getName() + ": " + this.name);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

public class ThreadFunc {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new MyThread3("t1"));
		Thread t2 = new Thread(new MyThread3("t2"));

		t2.setPriority(3);
		t2.setDaemon(true);// 设置守护线程
		t1.start(); // 异步启动线程
		// 获取线程名字、线程id、线程优先级
		System.out.println(t1.getName() + " " + t1.getId() + " "
				+ t1.getPriority());
		t1.yield();// 暂停线程执行，执行其它线程
		t2.start();

		t2.interrupt();

		try {
			if (t1.isAlive()) { // 判断线程是否存活
				t1.join(); // 线程回收
			}
			if (t2.isAlive()) {
				t2.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main out");

	}
}
