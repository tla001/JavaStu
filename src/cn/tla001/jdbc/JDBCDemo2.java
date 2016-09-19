package cn.tla001.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class JDBCDemo2 {
	private String urlString = "jdbc:mysql://localhost:3306/javastu";
	private String userString = "root";
	private String passwd = "root";

	@Test
	// 插入
	public void test1() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备预编译sql
		String sqlString = "insert contact(name,gender) values(?,?)";
		// 执行预编译指令
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		// 设置参数
		statement.setString(1, "李四");
		statement.setString(2, "男");
		// 发送sql语句，执行sql语句，得到返回结果
		statement.executeUpdate();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// 修改
	public void test2() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备预编译sql
		String sqlString = "update contact set name=? where id=?";
		// 执行预编译指令
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		// 设置参数
		statement.setString(1, "王五");
		statement.setInt(2, 3);
		// 发送sql语句，执行sql语句，得到返回结果
		statement.executeUpdate();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// 删除
	public void test3() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备预编译sql
		String sqlString = "delete from contact where id=?";
		// 执行预编译指令
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		// 设置参数
		statement.setInt(1, 3);
		// 发送sql语句，执行sql语句，得到返回结果
		statement.executeUpdate();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// 查询
	public void test4() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备预编译sql
		String sqlString = "select * from contact";
		// 执行预编译指令
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		// 发送sql语句，执行sql语句，得到返回结果
		ResultSet rSet = statement.executeQuery();
		while (rSet.next()) {
			// 根据列名称
			int id = rSet.getInt("id");
			String name = rSet.getString("name");
			String gender = rSet.getString("gender");
			System.out.println(id + " " + name + " " + gender);
		}
		if (rSet != null)
			rSet.close();
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}
}
