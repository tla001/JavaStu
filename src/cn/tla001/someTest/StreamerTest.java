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

	// ��ת��
	@Test
	public void converseStream() {
		String path = "./resources/test1.txt";
		try {
			// ���ֽ���תΪ�ַ���
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(path));
			osw.write("�й�����");
			System.out.println(osw.getEncoding());
			osw.close();
			osw = new OutputStreamWriter(new FileOutputStream(path, true),
					"GB2312");
			osw.write("�й�����");
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
			// ���ֽ���תΪ�ַ���
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
			 * flush��ָǿ�н�������е�����д���ļ�����ȥ�����д������Ļ�����һ��һ�е�д���ļ�
			 * ���ÿд��һ���־�ˢ��һ�Σ�������ˢ�µĻ����ܻ�������ݵĶ�ʧ
			 */
			fw.flush();
		}
		// ����ֲ�дflush()�ֲ�дclose()���򲻻�д���κ����ݵ��ı��ֻ��д���˻�����
		// fw.flush(); д������Ļ��������е�����һ��д���ļ�
		// fw.close(); close֮ǰ�����flush()
		// ���ַ�������������
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
				bw.write(s1); // д���ı��ļ�
				if (j == 10) {
					j = 0;
					bw.newLine(); // д��һ���зָ���
					bw.flush(); // ǿ��ˢ��
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
