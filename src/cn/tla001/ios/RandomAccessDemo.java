package cn.tla001.ios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessDemo {
	public static void main(String[] argvs) {

	}

	public void writeRandom(String path) throws IOException {
		File file = new File(path);
		RandomAccessFile raf = new RandomAccessFile(file, "w");
		String name = "tao";
		int age = 20;
		raf.writeBytes(name);
		raf.writeInt(age);
		raf.close();
	}

	public void readRandom(String path) throws IOException {
		File file = new File(path);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		raf.skipBytes(0);
		byte[] bs = new byte[3];
		for (int i = 0; i < bs.length; i++) {
			bs[i] = raf.readByte();
		}
		String name = new String(bs);
		int age = raf.readInt();
		raf.close();
	}
}
