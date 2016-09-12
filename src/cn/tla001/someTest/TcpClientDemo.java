package cn.tla001.someTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClientDemo {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		// �����ͻ��ˣ�ָ����������ַ�Ͷ˿�
		Socket sock = new Socket("localhost", 8888);
		// ��ȡ��������������������Ϣ
		OutputStream os = sock.getOutputStream();
		PrintWriter pw = new PrintWriter(os);// ���������װ�ɴ�ӡ��

		pw.write("user:tao passwd:123");
		pw.flush();
		sock.shutdownOutput();

		// ��ȡ������
		InputStream is = sock.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String info = null;
		while ((info = br.readLine()) != null) {
			System.out.println("server say: " + info);
		}
		// �ر���Դ
		br.close();
		isr.close();
		is.close();
		pw.close();
		os.close();
		sock.close();
	}
}
