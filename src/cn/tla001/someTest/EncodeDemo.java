package cn.tla001.someTest;

import java.io.UnsupportedEncodingException;

public class EncodeDemo {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = new String("老张abc");

		byte[] byte1 = str.getBytes();
		for (byte b : byte1) {
			System.out.print(Integer.toHexString(b & 0xff) + " ");
		}
		System.out.println();
		byte[] byte2 = str.getBytes("gbk");// 汉字两个字节，字母一个字节
		for (byte b : byte2) {
			System.out.print(Integer.toHexString(b & 0xff) + " ");
		}
		System.out.println();
		byte[] byte3 = str.getBytes("utf-8");// 汉字三个字节，字母一个字节
		for (byte b : byte3) {
			System.out.print(Integer.toHexString(b & 0xff) + " ");
		}
		System.out.println();
		byte[] byte4 = str.getBytes("utf-16be");// 汉字、字母两个字节
		for (byte b : byte4) {
			System.out.print(Integer.toHexString(b & 0xff) + " ");
		}
		System.out.println();
	}
}
