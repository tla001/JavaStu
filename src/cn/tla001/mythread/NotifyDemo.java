package cn.tla001.mythread;

class Service {
	public void testMethod(Object lock) {
		try {
			synchronized (lock) {
				System.out.println("begin wait() Thread name:"
						+ Thread.currentThread().getName());
				lock.wait();
				System.out.println("wait() end Thread name:"
						+ Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void syncNotifyMethod(Object lock) {
		try {
			synchronized (lock) {
				System.out.println("begin notify Thread name:"
						+ Thread.currentThread().getName());
				lock.notify();
				Thread.sleep(3000);
				System.out.println("end notify Thread name:"
						+ Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadC extends Thread {
	private Object lock;

	public ThreadC(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		Service service = new Service();
		service.testMethod(lock);
	}
}

class ThreadD extends Thread {
	private Object lock;

	public ThreadD(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		Service service = new Service();
		service.syncNotifyMethod(lock);
	}
}

public class NotifyDemo {
	public static void main(String[] args) {
		Object lock = new Object();

		ThreadC cc = new ThreadC(lock);
		cc.start();

		ThreadD dd = new ThreadD(lock);
		dd.start();

		try {
			cc.join();
			dd.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
