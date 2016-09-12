package cn.tla001.tserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		// ����serversocket
		ServerSocket server = new ServerSocket(8888);
		System.out.println("����������");
		while (true) {
			// ���ܿͻ�������
			Socket socket = server.accept();
			System.out.println("client..");
			// ��ȡ���ص�test.xml
			FileInputStream in = new FileInputStream(new File(
					"./resources/test.html"));
			// �����������ͨ��
			OutputStream out = socket.getOutputStream();
			// ��������
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.flush();
			out.close();
			in.close();
		}
		// socket.close();
	}
}
