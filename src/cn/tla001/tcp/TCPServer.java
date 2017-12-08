package cn.tla001.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

class ServerThread implements Runnable {
	private Socket client = null;

	public ServerThread(Socket client) {
		this.client = client;
		System.out.println("client ip:" + client.getInetAddress() + " port:"
				+ client.getPort());
	}

	@Override
	public void run() {
		try {
			// 获取Socket的输出流，用来向client发送数据
			PrintStream out = new PrintStream(client.getOutputStream());
			// 获取Socket的输入流，用来接收client的数据
			BufferedReader buf = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			boolean flag = true;
			while (flag) {
				String str = buf.readLine();
				if (str == null || "".equals(str)) {
					flag = false;
				} else {
					out.println("echo:" + str);
				}
			}
			out.close();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class TCPServer {
	public static void main(String[] args) throws IOException {
		int port = 9999;
		// /server监听
		ServerSocket server = new ServerSocket(port);
		Socket client = null;
		boolean flag = true;
		System.out.println("listen on prot " + port);
		while (flag) {
			client = server.accept();
			System.out.println("client connected!");
			new Thread(new ServerThread(client)).start();
		}
	}
}
