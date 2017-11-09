package cn.tla001.ios;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.SequenceInputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Vector;

import org.junit.Test;

public class StreamAndRender {

	// 输出流向文件写入
	@Test
	public void test1() throws IOException {
		File file = new File("file_test.txt");
		OutputStream out = new FileOutputStream(file);
		String str = "Hello World";
		byte[] bs = str.getBytes();
		out.write(bs);
		out.close();
	}

	// 输出流向文件追加
	@Test
	public void test2() throws IOException {
		File file = new File("file_test.txt");
		OutputStream out = new FileOutputStream(file, true);
		String str = "\n\nhello";
		byte[] bs = str.getBytes();
		out.write(bs);
		out.close();
	}

	// 输入流读取文件
	@Test
	public void test3() throws IOException {
		File file = new File("file_test.txt");
		InputStream in = new FileInputStream(file);
		byte[] bs = new byte[1024];
		int len = in.read(bs);
		in.close();
		System.out.println(new String(bs, 0, len));
	}

	// 令一种方式读入
	@Test
	public void test4() throws IOException {
		File file = new File("file_test.txt");
		InputStream in = new FileInputStream(file);
		byte[] bs = new byte[(int) file.length()];
		for (int i = 0; i < bs.length; i++) {
			bs[i] = (byte) in.read();
		}
		in.close();
		System.out.println(new String(bs, 0, bs.length));
	}

	// 令一种读入方式
	@Test
	public void test5() throws IOException {
		File file = new File("file_test.txt");
		InputStream in = new FileInputStream(file);
		byte[] bs = new byte[1024];
		int tmp = 0;
		int len = 0;
		while ((tmp = in.read()) != -1) {
			bs[len] = (byte) tmp;
			len++;
		}
		in.close();
		System.out.println(new String(bs));
	}

	// 字节流复制文件
	@Test
	public void fileCopy() {
		byte[] buff = new byte[512];
		int len = 0;
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(new File("file_test.txt"));
			out = new FileOutputStream(new File("file_test_bak.txt"));

			while ((len = in.read(buff)) != -1) {
				out.write(buff, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 字节流的对象输入输出
	@Test
	public void test6() {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(
					"student.txt")));
			out.writeObject(new Student("tao", 12));
			out.writeObject(new Student("zheng", 13));

			in = new ObjectInputStream(new FileInputStream(new File(
					"student.txt")));
			for (int i = 0; i < 2; i++) {
				System.out.println(in.readObject());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 字节流的对象数据输入输出
	@Test
	public void test7() {
		DataInputStream in = null;
		DataOutputStream out = null;

		Student[] stus = { new Student("tao", 12), new Student("zheng", 13) };
		try {
			out = new DataOutputStream(new FileOutputStream(
					new File("data.txt")));
			for (Student stu : stus) {
				out.writeUTF(stu.getName());
				out.writeInt(stu.getAge());
			}
			out.flush();
			out.close();

			in = new DataInputStream(new FileInputStream(new File("data.txt")));
			Student[] newStus = new Student[stus.length];
			for (int i = 0; i < newStus.length; i++) {
				newStus[i] = new Student(in.readUTF(), in.readInt());
			}
			for (Student stu : newStus) {
				System.out.println(stu);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	// 合并流
	@Test
	public void test8() {
		SequenceInputStream in = null;
		BufferedOutputStream out = null;
		try {
			Vector<InputStream> v = new Vector<InputStream>();
			v.addElement(new FileInputStream(new File("student.txt")));
			v.addElement(new FileInputStream(new File("data.txt")));
			Enumeration<InputStream> e = v.elements();

			in = new SequenceInputStream(e);
			out = new BufferedOutputStream(new FileOutputStream("seq.txt"));

			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 字符流写文件
	@Test
	public void test9() {
		Writer out = null;
		try {
			out = new FileWriter(new File("data.txt"));
			String str = "Hello World";
			out.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 字符流读取，打印到屏幕
	@Test
	public void test10() {
		char[] buff = new char[512];
		int len = 0;
		FileReader reader = null;
		PrintWriter printer = null;
		try {
			reader = new FileReader(new File("data.txt"));
			printer = new PrintWriter(System.out);

			while ((len = reader.read(buff)) != -1) {
				printer.write(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				printer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 带缓冲的输入流
	@Test
	public void test11() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
				"data_bak.txt")));
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"data.txt")));
		String str;
		while ((str = reader.readLine()) != null) {
			writer.write(str);
			writer.flush();
		}
		writer.close();
		reader.close();
	}

}

class Student implements Serializable {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Student(String _name, int _age) {
		super();
		this.name = _name;
		this.age = _age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + " age=" + age + "]";
	}
}