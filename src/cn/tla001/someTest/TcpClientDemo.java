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
		// 创建客户端，指定服务器地址和端口
		Socket sock = new Socket("localhost", 8888);
		// 获取输出流，向服务器发送信息
		OutputStream os = sock.getOutputStream();
		PrintWriter pw = new PrintWriter(os);// 将输出流包装成打印流

		pw.write("user:tao passwd:123");
		pw.flush();
		sock.shutdownOutput();

		// 获取输入流
		InputStream is = sock.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String info = null;
		while ((info = br.readLine()) != null) {
			System.out.println("server say: " + info);
		}
		// 关闭资源
		br.close();
		isr.close();
		is.close();
		pw.close();
		os.close();
		sock.close();
	}
}
