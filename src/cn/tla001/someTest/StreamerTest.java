package cn.tla001.someTest;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class StreamerTest {
	public static void main(String[] args) {

	}

	// 流转换
	@Test
	public void converseStream() {
		String path = "./resources/test1.txt";
		try {
			// 将字节流转为字符流
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(path));
			osw.write("中国北京");
			System.out.println(osw.getEncoding());
			osw.close();
			osw = new OutputStreamWriter(new FileOutputStream(path, true),
					"GB2312");
			osw.write("中国北京");
			System.out.println(osw.getEncoding());
			osw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// 将字节流转为字符流
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					path), "GB2312");
			int c;
			while ((c = isr.read()) != -1) {
				System.out.print((char) c);
			}
			System.out.println();
			isr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// FileReader
	@Test
	public void fileReaderTest() throws IOException {
		String path = "./resources/test2.txt";
		FileWriter fw = new FileWriter(path);
		FileReader fr = new FileReader(path);
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= i; j++) {
				String str = i + "*" + j + "=" + i * j + " ";
				fw.write(str);
			}
			fw.write("\r\n");
			/*
			 * flush是指强行将输出流中的数据写到文件里面去。如果写到这里的话就是一行一行的写入文件
			 * 最好每写完一部分就刷新一次，如果最后刷新的话可能会造成数据的丢失
			 */
			fw.flush();
		}
		// 如果又不写flush()又不写close()，则不会写入任何内容到文本里。只是写到了缓冲区
		// fw.flush(); 写到这里的话就是所有的内容一起写进文件
		// fw.close(); close之前会调用flush()
		// 读字符输入流的数据
		int c;
		while ((c = fr.read()) != -1) {
			System.out.print((char) c);
		}
	}

	// BufferedReader
	@Test
	public void bufferReaderTest() throws IOException {
		String path = "./resources/test3.txt";
		BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		int j = 0;
		for (int i = 2; i < 100; i++) {
			if (isPrime(i)) {
				j++;
				String s = String.valueOf(i);
				String s1 = s + " ";
				bw.write(s1); // 写入文本文件
				if (j == 10) {
					j = 0;
					bw.newLine(); // 写入一个行分隔符
					bw.flush(); // 强制刷新
				}
			}
		}

		bw.close();
	}

	public boolean isPrime(int n) {
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
