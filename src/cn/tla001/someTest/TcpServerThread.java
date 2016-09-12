package cn.tla001.someTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerThread {
	public static void main(String[] args) throws IOException {
		// 创建一个服务器端的socket，绑定指定端口，并监听此端口
		ServerSocket sersock = new ServerSocket(8080);
		Socket sock = null;
		// 记录客户端数量
		int count = 0;
		// 调用accept开始监听，等待客户端连接
		System.out.println("===========等待客户端连接============");
		while (true) {
			sock = sersock.accept();
			// 创建一个新的线程
			ServerThread st = new ServerThread(sock);
			st.start();
			count++;
			System.out.print("cilent number: " + count);
		}
	}
}

/**
 * 线程处理类
 * 
 * @author Administrator
 * 
 */
class ServerThread extends Thread {
	private Socket sock = null;

	public ServerThread(Socket sock) {
		this.sock = sock;
	}

	// 线程执行的操作，响应客户端请求
	public void run() {
		try {
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
		} catch (IOException e) {

		}
	}
}