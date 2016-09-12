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
		// ����һ���������˵�socket����ָ���˿ڣ��������˶˿�
		ServerSocket sersock = new ServerSocket(8080);
		Socket sock = null;
		// ��¼�ͻ�������
		int count = 0;
		// ����accept��ʼ�������ȴ��ͻ�������
		System.out.println("===========�ȴ��ͻ�������============");
		while (true) {
			sock = sersock.accept();
			// ����һ���µ��߳�
			ServerThread st = new ServerThread(sock);
			st.start();
			count++;
			System.out.print("cilent number: " + count);
		}
	}
}

/**
 * �̴߳�����
 * 
 * @author Administrator
 * 
 */
class ServerThread extends Thread {
	private Socket sock = null;

	public ServerThread(Socket sock) {
		this.sock = sock;
	}

	// �߳�ִ�еĲ�������Ӧ�ͻ�������
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
		} catch (IOException e) {

		}
	}
}