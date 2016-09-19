package cn.tla001.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class JDBCDemo1 {
	private String urlString = "jdbc:mysql://localhost:3306/javastu";
	private String userString = "root";
	private String passwd = "root";

	@Test
	public void test1() throws SQLException {
		// 创建驱动对象
		Driver driver = new com.mysql.jdbc.Driver();
		// 设置用户名和密码
		Properties properties = new Properties();
		properties.setProperty("user", userString);
		properties.setProperty("passwd", passwd);

		// 连接数据库
		Connection connection = driver.connect(urlString, properties);
		System.out.println(connection);
	}

	@Test
	public void test2() {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (Exception e) {
			System.out.println("无法加载驱动");
		}

		try {
			Connection con = DriverManager.getConnection(urlString, userString,
					passwd);
			if (!con.isClosed())
				System.out.println("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test3() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 创建statement对象
		java.sql.Statement statement = connection.createStatement();
		// 准备sql
		String sqlString = "create table contact(id int primary key auto_increment,name varchar(20),gender varchar(2))";
		// String sqlString = "drop table contact";
		// 发送sql语句，执行sql语句，得到返回结果
		int count = statement.executeUpdate(sqlString);
		// 输出
		System.out.println(count);
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	public void test4() throws SQLException, ClassNotFoundException {
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 创建statement对象
		java.sql.Statement statement = connection.createStatement();
		// 准备sql
		String sqlString = "insert contact(name,gender) values('王二丫','女')";

		// 发送sql语句，执行sql语句，得到返回结果
		int count = statement.executeUpdate(sqlString);
		// 输出
		System.out.println(count);
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	public void test5() throws SQLException, ClassNotFoundException {
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 创建statement对象
		java.sql.Statement statement = connection.createStatement();
		// 准备sql
		String sqlString = "select * from contact";

		// 发送sql语句，执行sql语句，得到返回结果
		ResultSet rSet = statement.executeQuery(sqlString);
		// 移动光标
		while (rSet.next()) {
			// 根据索引取值
			// int id = rSet.getInt(1);
			// String name = rSet.getString(2);
			// String gender = rSet.getString(3);
			// System.out.println(id + " " + name + " " + gender);

			// 根据列名称
			int id = rSet.getInt("id");
			String name = rSet.getString("name");
			String gender = rSet.getString("gender");
			System.out.println(id + " " + name + " " + gender);
		}
		// 输出

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}
}
