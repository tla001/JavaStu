package cn.tla001.mythread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class Task extends TimerTask {
	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println("Current time:" + sdf.format(new Date()));
	}
}

public class TimerTest {
	public static void main(String[] args) {
		Timer t = new Timer();

		Task task1 = new Task();
		t.schedule(task1, 1000, 2000);

		t.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 1000, 1000);
	}
}
