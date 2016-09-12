package cn.tla001.someTest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServerDemo {
	public static void main(String[] args) throws IOException {
		// 创建服务器端DatagramSocket 指定端口
		DatagramSocket sock = new DatagramSocket(8800);
		// 创建数据报，用于接收客户端发送的数据
		byte[] data = new byte[1024];// 创建字节数组，指定接收数据包的大小
		DatagramPacket pack = new DatagramPacket(data, data.length);
		// 接收客户端发送的数据
		sock.receive(pack);
		// 读取数据
		String info = new String(data, 0, pack.getLength());
		System.out.println("client say: " + info);
	}
}
