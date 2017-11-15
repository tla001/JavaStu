package cn.tla001.mythread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

class Producer extends Thread {
	private PipedOutputStream pipe;
	private int index = 0;

	public Producer(PipedOutputStream pipe) {
		super();
		this.pipe = pipe;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 20; i++)
				pipe.write(index++);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Consumer extends Thread {
	private PipedInputStream pipe;

	public Consumer(PipedInputStream pipe) {
		super();
		this.pipe = pipe;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 20; i++)
				System.out.println(pipe.read());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class ConmunicationWithPipe {
	public static void main(String[] args) {

		PipedOutputStream out = new PipedOutputStream();
		PipedInputStream in = new PipedInputStream();

		try {
			out.connect(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Producer p = new Producer(out);
		Consumer c = new Consumer(in);

		p.start();
		c.start();
	}
}
