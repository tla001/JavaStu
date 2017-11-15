package cn.tla001.mythread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class TaskThread implements Runnable {
	private int taskNum;

	public TaskThread(int num) {
		this.taskNum = num;
	}

	@Override
	public void run() {
		System.out.println("Task " + taskNum + " is running");

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Task " + taskNum + " done");
	}
}

public class ThreadPoolTest {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200,
				TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

		for (int i = 0; i < 15; i++) {
			executor.execute(new TaskThread(i));
			System.out.println("threads in pool:" + executor.getPoolSize()
					+ " threads in queues:" + executor.getQueue().size()
					+ " threads done:" + executor.getCompletedTaskCount());
		}
		executor.shutdown();
	}
}
