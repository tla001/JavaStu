package cn.tla001.someTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo {
	public static void main(String[] args) throws IOException {
		// ����һ���������˵�socket����ָ���˿ڣ��������˶˿�
		ServerSocket sersock = new ServerSocket(8888);
		// ����accept��ʼ�������ȴ��ͻ�������
		System.out.println("===========�ȴ��ͻ�������============");
		Socket sock = sersock.accept();
		// ��ȡ����������ȡ�ͻ��˷�������Ϣ
		InputStream is = sock.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String info = null;
		while ((info = br.readLine()) != null) {
			System.out.println("client say: " + info);
		}
		sock.shutdownInput();
		// ��ȡ���������Ӧ�ͻ���
		OutputStream os = sock.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.write("welcome");
		pw.flush();

		pw.close();
		os.close();
		br.close();
		isr.close();
		is.close();
		sock.close();
		sersock.close();
	}
}
