package cn.tla001.mythread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest2 {
	public static void main(String[] args) {
		ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
		ExecutorService threadPool2 = Executors.newCachedThreadPool();
		ExecutorService threadPool = Executors.newSingleThreadExecutor();

		for (int i = 0; i < 10; i++) {
			final int task = i;
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					for (int j = 1; j <= 10; j++) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()
								+ " is looping of " + j + " for task of "
								+ task);
					}
				}
			});
		}

		System.out.println("all of 10 tasks done");

		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("begining");
			}
		}, 6, 2, TimeUnit.SECONDS);
	}
}
