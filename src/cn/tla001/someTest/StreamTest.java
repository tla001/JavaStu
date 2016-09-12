package cn.tla001.someTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

public class StreamTest {
	public static void main(String[] args) {

	}

	// FileInputStream
	@Test
	public void copyFile() {
		byte[] buffer = new byte[512];
		int num = 0;

		try {
			FileInputStream input = new FileInputStream(new File(
					"./resources/lufi.jpg"));
			FileOutputStream out = new FileOutputStream(new File(
					"./resources/lufi_bak.jpg"));
			try {
				while ((num = input.read(buffer)) != -1) {
					out.write(buffer, 0, num);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				input.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ObjectInputStream
	@Test
	public void objTest() {
		try {
			ObjectOutputStream objWriter = new ObjectOutputStream(
					new FileOutputStream(new File("./resources/student.txt")));
			objWriter.writeObject(new Student("gg", 22));
			objWriter.writeObject(new Student("tt", 12));
			objWriter.writeObject(new Student("rr", 14));
			ObjectInputStream objReader = new ObjectInputStream(
					new FileInputStream(new File("./resources/student.txt")));

			for (int i = 0; i < 3; i++) {
				try {
					System.out.println(objReader.readObject());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			objWriter.close();
			objReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// DataStream
	@Test
	public void DataStreamDemo() throws IOException {
		String names[] = { new String("gg"), new String("tt"), new String("aa") };
		try {
			DataOutputStream dout = new DataOutputStream(new FileOutputStream(
					new File("./resources/data.txt")));
			for (String name : names) {
				dout.writeUTF(name);
			}
			dout.flush();
			dout.close();
			DataInputStream din = new DataInputStream(new FileInputStream(
					new File("./resources/data.txt")));
			for (int i = 0; i < names.length; i++) {
				System.out.println(din.readUTF());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Student implements Serializable {
	private String name;
	private int age;

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
}
