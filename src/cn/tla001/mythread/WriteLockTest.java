package cn.tla001.mythread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteLockTest {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void get(Thread thread) {
		lock.readLock().lock();
		try {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start <= 1) {
				System.out.println(Thread.currentThread().getName() + " read");
			}
			System.out.println(Thread.currentThread().getName() + " read over");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();
		}
	}

	public static void main(String[] args) {
		final WriteLockTest test = new WriteLockTest();
		new Thread() {
			public void run() {
				test.get(Thread.currentThread());
			}
		}.start();

		new Thread() {
			public void run() {
				test.get(Thread.currentThread());
			}
		}.start();
	}
}
