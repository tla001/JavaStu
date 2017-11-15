package cn.tla001.mythread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	private ArrayList<Integer> list = new ArrayList<Integer>();
	private Lock lock = new ReentrantLock();

	public void insert(Thread thread) {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " Lock");
			for (int i = 0; i < 5; i++) {
				list.add(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + " Unlock");
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final LockTest test = new LockTest();

		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			}
		}.start();

		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			}
		}.start();

	}
}
