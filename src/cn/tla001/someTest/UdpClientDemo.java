package cn.tla001.someTest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClientDemo {
	public static void main(String[] args) throws IOException {
		// 1定义服务器地址，端口号，数据
		InetAddress addr = InetAddress.getByName("localhost");
		int port = 8800;
		byte[] data = "user:tao    passwd:123".getBytes();
		// 2创建数据报，包含发送的数据信息
		DatagramPacket pack = new DatagramPacket(data, data.length, addr, port);
		// 3创建datagramSocket对象
		DatagramSocket sock = new DatagramSocket();
		// 4向服务器发送数据报
		sock.send(pack);
	}
}
