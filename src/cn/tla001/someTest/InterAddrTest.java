package cn.tla001.someTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Date;

import org.junit.Test;

public class InterAddrTest {
	public static void main(String[] args) throws UnknownHostException,
			IOException {

	}

	@Test
	public void InterAddressTest() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		System.out.println("localhost:" + addr);
		addr = InetAddress.getByName("osborne.com");
		System.out.println(addr);
		InetAddress sw[] = InetAddress.getAllByName("www.baidu.com");
		for (int i = 0; i < sw.length; i++)
			System.out.println(sw[i]);
	}

	@Test
	public void whois() throws UnknownHostException, IOException {
		int c;
		Socket s = new Socket("www.internic.net", 43);
		InputStream in = s.getInputStream();
		OutputStream out = s.getOutputStream();
		String site = "osborne.com" + "\n";
		byte buf[] = site.getBytes();
		out.write(buf);
		while ((c = in.read()) != -1) {
			System.out.println((char) c);
		}
		s.close();
	}

	@Test
	public void URLDemo() throws MalformedURLException {
		URL hp = new URL("http://www.osborne.com/dowload");

		System.out.println("Protocol: " + hp.getProtocol());
		// 如果未设置端口，使用默认端口，但是此时getPort返回值为-1
		System.out.println("Port: " + hp.getPort());
		System.out.println("Host: " + hp.getHost());
		System.out.println("File: " + hp.getFile());
		System.out.println("Ext: " + hp.toExternalForm());
	}

	@Test
	public void URLDemo2() throws IOException {
		URL url = new URL("http://www.baidu.com");
		InputStream in = url.openStream();
		// 将字节流转化为字符流
		InputStreamReader isr = new InputStreamReader(in, "utf-8");
		// 添加缓冲，提高读取效率
		BufferedReader br = new BufferedReader(isr);
		String data = br.readLine();
		while (data != null) {
			System.out.println(data);
			data = br.readLine();
		}
		br.close();
		isr.close();
		in.close();
	}

	@Test
	public void URLConnetion() throws IOException {
		int c;
		URL hp = new URL("http://www.baidu.com");
		URLConnection hpCon = hp.openConnection();

		System.out.println("Date: " + new Date(hpCon.getDate()));
		System.out.println("Content-Type: " + hpCon.getContentType());
		System.out.println("Expires: " + hpCon.getExpiration());
		System.out.println("last-modified: "
				+ new Date(hpCon.getLastModified()));
		int len = hpCon.getContentLength();
		System.out.println("Content-Length: " + len);
		if (len > 0) {
			System.out.println("=====Content=====");
			InputStream input = hpCon.getInputStream();
			int i = len;
			while (((c = input.read()) != -1) && (--i > 0)) {
				System.out.print((char) c);
			}
			input.close();
		} else {
			System.out.println("NO Content Available");
		}
	}
}
