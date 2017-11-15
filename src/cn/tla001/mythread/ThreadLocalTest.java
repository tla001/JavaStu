package cn.tla001.mythread;

class ThreadLocalVar {
	private static ThreadLocal<Integer> n = new ThreadLocal<Integer>() {
		@Override
		public Integer initialValue() {
			return 0;
		}
	};

	public int getNextNum() {
		n.set(n.get() + 1);
		return n.get();
	}
}

class Client extends Thread {
	private ThreadLocalVar v;

	public Client(ThreadLocalVar v) {
		super();
		this.v = v;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " "
					+ v.getNextNum());
		}
	}
}

public class ThreadLocalTest {
	public static void main(String[] args) {
		ThreadLocalVar v = new ThreadLocalVar();
		for (int i = 0; i < 3; i++) {
			new Client(v).start();
		}
	}
}
