package cn.tla001.mythread;

class MyThread3 implements Runnable {
	private String name;

	public MyThread3(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// ��̬��������ȡ�߳�����
		System.out.println(Thread.currentThread().getName() + ": " + this.name);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

public class ThreadFunc {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new MyThread3("t1"));
		Thread t2 = new Thread(new MyThread3("t2"));

		t2.setPriority(3);
		t2.setDaemon(true);// �����ػ��߳�
		t1.start(); // �첽�����߳�
		// ��ȡ�߳����֡��߳�id���߳����ȼ�
		System.out.println(t1.getName() + " " + t1.getId() + " "
				+ t1.getPriority());
		t1.yield();// ��ͣ�߳�ִ�У�ִ�������߳�
		t2.start();

		t2.interrupt();

		try {
			if (t1.isAlive()) { // �ж��߳��Ƿ���
				t1.join(); // �̻߳���
			}
			if (t2.isAlive()) {
				t2.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main out");

	}
}
