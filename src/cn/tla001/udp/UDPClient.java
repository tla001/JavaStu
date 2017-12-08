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
		// 客户端监听端口数据
		DatagramSocket ds = new DatagramSocket(9001);
		InetAddress loc = InetAddress.getLocalHost();
		// 定义用来发送数据的DatagramPacket实例
		DatagramPacket dp_send = new DatagramPacket(str.getBytes(),
				str.length(), loc, 3000);
		// 定义用来接收数据的DatagramPacket实例
		DatagramPacket dp_rec = new DatagramPacket(buf, 1024);
		// 数据发向本地3000端口
		ds.setSoTimeout(TIMEOUT);// 设置接收数据时阻塞的最长时间
		int tryNum = 0;
		boolean recRes = false;
		while (!recRes && tryNum < MAXNUM) {
			// 发送数据
			ds.send(dp_send);
			try {
				ds.receive(dp_rec);
				if (!dp_rec.getAddress().equals(loc)) {
					throw new IOException("Rec packet form unknown source");
				}
				recRes = true;
			} catch (InterruptedIOException e) {
				// 超时重发
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
			// 由于dp_receive在接收了数据之后，其内部消息长度值会变为实际接收的消息的字节数，
			// 所以这里要将dp_receive的内部消息长度重新置为1024
			dp_rec.setLength(1024);
		} else {
			System.out.println("No response");
		}
		ds.close();
	}
}
