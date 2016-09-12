package cn.tla001.someTest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClientDemo {
	public static void main(String[] args) throws IOException {
		// 1�����������ַ���˿ںţ�����
		InetAddress addr = InetAddress.getByName("localhost");
		int port = 8800;
		byte[] data = "user:tao    passwd:123".getBytes();
		// 2�������ݱ����������͵�������Ϣ
		DatagramPacket pack = new DatagramPacket(data, data.length, addr, port);
		// 3����datagramSocket����
		DatagramSocket sock = new DatagramSocket();
		// 4��������������ݱ�
		sock.send(pack);
	}
}
