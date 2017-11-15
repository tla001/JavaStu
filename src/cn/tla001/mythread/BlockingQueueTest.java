package cn.tla001.mythread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
	public static class Producer implements Runnable {
		private final BlockingQueue<Integer> bq;
		private volatile boolean flag;
		private Random random;

		public Producer(BlockingQueue<Integer> bq) {
			this.bq = bq;
			flag = false;
			random = new Random();
		}

		@Override
		public void run() {
			while (!flag) {
				int info = random.nextInt(100);
				try {
					bq.put(info);
					System.out.println(Thread.currentThread().getName()
							+ " produce " + info);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {

				}
			}
		}

		public void shutDown() {
			flag = true;

		}
	}

	public static class Consumer implements Runnable {
		public final BlockingQueue<Integer> bq;
		private volatile boolean flag;

		public Consumer(BlockingQueue<Integer> bq) {
			this.bq = bq;
			flag = false;
		}

		@Override
		public void run() {
			while (!flag) {
				int info;
				try {
					info = bq.take();
					System.out.println(Thread.currentThread().getName()
							+ " take " + info);
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void shutDown() {
			flag = true;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> bq = new LinkedBlockingQueue<Integer>(10);
		Producer p = new Producer(bq);
		Consumer c = new Consumer(bq);

		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				new Thread(p, "producer " + i).start();
			} else {
				new Thread(c, "consumer " + (10 - i)).start();
			}
		}

		Thread.sleep(10000);

		p.shutDown();
		c.shutDown();
	}
}
