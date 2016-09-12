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
		// 创建一个服务器端的socket，绑定指定端口，并监听此端口
		ServerSocket sersock = new ServerSocket(8888);
		// 调用accept开始监听，等待客户端连接
		System.out.println("===========等待客户端连接============");
		Socket sock = sersock.accept();
		// 获取输入流，读取客户端发来的信息
		InputStream is = sock.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String info = null;
		while ((info = br.readLine()) != null) {
			System.out.println("client say: " + info);
		}
		sock.shutdownInput();
		// 获取输出流，响应客户端
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
