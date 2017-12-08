package cn.tla001.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPClient {
	public static void main(String[] args) throws IOException {
		// ����socket
		Socket client = new Socket("127.0.0.1", 9999);
		client.setSoTimeout(100000);
		// ��ȡ��������
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		// ��ȡSocket������������������ݵ�server
		PrintStream out = new PrintStream(client.getOutputStream());
		// ��ȡSocket����������������server���ͻ���������
		BufferedReader buf = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		boolean flag = true;
		while (flag) {
			System.out.print("Input:");
			String str = input.readLine();
			// ��������
			out.println(str);
			if ("bye".equals(str)) {
				flag = false;
			} else {
				try {
					// ��ʱ���׳��쳣
					String echo = buf.readLine();
					System.out.println(echo);
				} catch (SocketTimeoutException e) {
					System.out.println("Time out, No response");
				}
			}
		}

		input.close();
		if (client != null) {
			client.close();
		}
	}
}
