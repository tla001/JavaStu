package cn.tla001.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPClient {
	public static void main(String[] args) throws IOException {
		// 创建socket
		Socket client = new Socket("127.0.0.1", 9999);
		client.setSoTimeout(100000);
		// 获取键盘输入
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		// 获取Socket输出流，用来发送数据到server
		PrintStream out = new PrintStream(client.getOutputStream());
		// 获取Socket输入流，用来接收server发送回来的数据
		BufferedReader buf = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		boolean flag = true;
		while (flag) {
			System.out.print("Input:");
			String str = input.readLine();
			// 发送数据
			out.println(str);
			if ("bye".equals(str)) {
				flag = false;
			} else {
				try {
					// 超时会抛出异常
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
