package cn.tla001.udp;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	private static final int TIMEOUT = 5000;
	private static final int MAXNUM = 5;

	public static void main(String[] args) throws IOException {
		String str = "Hello UDPServer";
		byte[] buf = new byte[1024];
		// �ͻ��˼����˿�����
		DatagramSocket ds = new DatagramSocket(9001);
		InetAddress loc = InetAddress.getLocalHost();
		// ���������������ݵ�DatagramPacketʵ��
		DatagramPacket dp_send = new DatagramPacket(str.getBytes(),
				str.length(), loc, 3000);
		// ���������������ݵ�DatagramPacketʵ��
		DatagramPacket dp_rec = new DatagramPacket(buf, 1024);
		// ���ݷ��򱾵�3000�˿�
		ds.setSoTimeout(TIMEOUT);// ���ý�������ʱ�������ʱ��
		int tryNum = 0;
		boolean recRes = false;
		while (!recRes && tryNum < MAXNUM) {
			// ��������
			ds.send(dp_send);
			try {
				ds.receive(dp_rec);
				if (!dp_rec.getAddress().equals(loc)) {
					throw new IOException("Rec packet form unknown source");
				}
				recRes = true;
			} catch (InterruptedIOException e) {
				// ��ʱ�ط�
				tryNum++;
				System.out.println("Time out,and try " + tryNum + " times");
			}
		}

		if (recRes) {
			System.out.println("client rec data from server:");
			System.out.println(new String(dp_rec.getData(), 0, dp_rec
					.getLength())
					+ " from "
					+ dp_rec.getAddress()
					+ ":"
					+ dp_rec.getPort());
			// ����dp_receive�ڽ���������֮�����ڲ���Ϣ����ֵ���Ϊʵ�ʽ��յ���Ϣ���ֽ�����
			// ��������Ҫ��dp_receive���ڲ���Ϣ����������Ϊ1024
			dp_rec.setLength(1024);
		} else {
			System.out.println("No response");
		}
		ds.close();
	}
}
