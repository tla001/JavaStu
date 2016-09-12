package cn.tla001.someTest;

import java.io.IOException;

public class ExecDemo {
	public static void main(String[] args) {
		Runtime r = Runtime.getRuntime();
		Process p = null;

		try {
			p = r.exec("notepad");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
