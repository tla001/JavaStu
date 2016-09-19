package cn.tla001.jdbc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class JDBCDemo6 {
	private String urlString = "jdbc:mysql://localhost:3306/javastu";
	private String userString = "root";
	private String passwd = "root";

	@Test
	// 1. 保存大文本数据类型 ( 写longtext)
	public void test1() throws ClassNotFoundException, SQLException,
			FileNotFoundException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备预编译sql
		String sqlString = "insert longtest(content) values(?)";
		// 执行预编译指令
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		// 设置参数
		// String path = JDBCDemo6.class.getResource("/resources/code.txt")
		// .getPath();
		String path = "./resources/code.txt";
		System.out.println(path);
		FileReader fReader = new FileReader(new File(path));
		statement.setCharacterStream(1, fReader);
		// 发送sql语句，执行sql语句，得到返回结果
		statement.executeUpdate();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// 1. 读取大文本数据类型 ( 写longtext)
	public void test2() throws ClassNotFoundException, SQLException,
			FileNotFoundException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备预编译sql
		String sqlString = "select * from longtest";
		// 执行预编译指令
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		ResultSet rSet = statement.executeQuery();
		while (rSet.next()) {
			System.out.println(rSet.getString("content"));
		}

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// 1.保存二进制文件 ( 写longtext)
	public void test3() throws ClassNotFoundException, SQLException,
			IOException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString
				+ "?useUnicode=true&characterEncoding=UTF-8", userString,
				passwd);
		// 准备预编译sql
		String sqlString = "insert longtest(img) values(?)";
		// 执行预编译指令
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		// 设置参数
		String path = "./resources/lufi.jpg";
		System.out.println(path);
		File file = new File(path);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		statement.setBinaryStream(1, in, (int) file.length());
		// 发送sql语句，执行sql语句，得到返回结果
		statement.executeUpdate();
		in.close();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// 1. 读取二进制数据类型 ( 写longtext)
	public void test4() throws ClassNotFoundException, SQLException,
			IOException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString
				+ "?useUnicode=true&characterEncoding=UTF-8", userString,
				passwd);
		// 准备预编译sql
		String sqlString = "select img from longtest where id=4";
		// 执行预编译指令
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		ResultSet rSet = statement.executeQuery();
		while (rSet.next()) {
			InputStream inputStream = rSet.getBinaryStream("img");
			FileOutputStream outputStream = new FileOutputStream(new File(
					"d:/1.jpg"));
			int len = -1;
			byte buf[] = new byte[1024];
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
			inputStream.close();
		}

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}
}