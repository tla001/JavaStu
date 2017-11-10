package cn.tla001.mythread;

class SelfNum {
	private int num = 0;

	synchronized public void add(String userName) {
		try {
			if (userName.equals("a")) {
				num = 100;
				System.out.println("a set over");
				Thread.sleep(2000);
			} else if (userName.equals("b")) {
				num = 200;
				System.out.print("b set over");
			}
			System.out.println("num = " + num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadA extends Thread {
	private SelfNum num;

	public ThreadA(SelfNum num) {
		super();
		this.num = num;
	}

	@Override
	public void run() {
		super.run();
		num.add("a");
	}
}

class ThreadB extends Thread {
	private SelfNum num;

	public ThreadB(SelfNum num) {
		super();
		this.num = num;
	}

	@Override
	public void run() {
		super.run();
		num.add("b");
	}
}

public class ThreadSync {
	public static void main(String[] args) {
		SelfNum num = new SelfNum();
		Thread threadA = new ThreadA(num);
		Thread threadB = new ThreadB(num);

		threadA.start();
		threadB.start();
	}
}
