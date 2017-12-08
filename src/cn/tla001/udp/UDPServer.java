package cn.tla001.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
	public static void main(String[] args) throws IOException {
		String str = "Hello UDPClient";
		byte[] buf = new byte[1024];
		// 服务端监听在3000端口
		DatagramSocket ds = new DatagramSocket(3000);
		// 接收客户端发送过来的数据
		DatagramPacket dp_rec = new DatagramPacket(buf, 1024);
		System.out.println("server is waiting for client...");
		boolean flag = true;
		while (flag) {
			ds.receive(dp_rec);
			System.out.println("server rec data from client:");
			System.out.println(new String(dp_rec.getData(), 0, dp_rec
					.getLength())
					+ " from "
					+ dp_rec.getAddress()
					+ ":"
					+ dp_rec.getPort());
			// 数据发送到数据的9000端口
			DatagramPacket dp_send = new DatagramPacket(str.getBytes(),
					str.length(), dp_rec.getAddress(), 9001);
			ds.send(dp_send);
			dp_rec.setLength(1024);
		}
		ds.close();
	}
}
