package cn.tla001.mythread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Business {
	private boolean flag = true;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private int seq = 0;

	public void sub(int loop) {
		lock.lock();
		try {
			while (!flag) {
				condition.await();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println("loop " + loop + " sub thread seq " + seq--);
			}
			flag = false;
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void add(int loop) {
		lock.lock();
		try {
			while (flag) {
				condition.await();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println("loop " + loop + " add thread seq " + seq++);
			}
			flag = true;
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

public class ConditionTest {
	public static void main(String[] args) {
		final Business b = new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					threadExecute(b, "add");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					threadExecute(b, "sub");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void threadExecute(Business b, String threadTyep)
			throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			if ("add".equals(threadTyep)) {
				b.add(i);
			} else {
				b.sub(i);
			}
		}
	}
}
