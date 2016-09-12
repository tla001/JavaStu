package cn.tla001.someTest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServerDemo {
	public static void main(String[] args) throws IOException {
		// ������������DatagramSocket ָ���˿�
		DatagramSocket sock = new DatagramSocket(8800);
		// �������ݱ������ڽ��տͻ��˷��͵�����
		byte[] data = new byte[1024];// �����ֽ����飬ָ���������ݰ��Ĵ�С
		DatagramPacket pack = new DatagramPacket(data, data.length);
		// ���տͻ��˷��͵�����
		sock.receive(pack);
		// ��ȡ����
		String info = new String(data, 0, pack.getLength());
		System.out.println("client say: " + info);
	}
}
