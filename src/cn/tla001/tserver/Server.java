package cn.tla001.tserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		// 创建serversocket
		ServerSocket server = new ServerSocket(8888);
		System.out.println("服务器启动");
		while (true) {
			// 接受客户端连接
			Socket socket = server.accept();
			System.out.println("client..");
			// 读取本地的test.xml
			FileInputStream in = new FileInputStream(new File(
					"./resources/test.html"));
			// 创建数据输出通道
			OutputStream out = socket.getOutputStream();
			// 发送数据
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
