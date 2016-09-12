package cn.tla001.someTest;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
	public static void main(String[] args) {
		MyTimerTask task = new MyTimerTask();
		Timer t = new Timer();
		t.schedule(task, 1000, 500);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.cancel();
	}
}

class MyTimerTask extends TimerTask {
	public void run() {
		System.out.println("Timer task executed");
	}
}
