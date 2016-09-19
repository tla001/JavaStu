package cn.tla001.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

public class JDBCDemo5 {
	private String urlString = "jdbc:mysql://localhost:3306/javastu";
	private String userString = "root";
	private String passwd = "root";

	@Test
	// 转账，没有使用事务
	public void test1() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备预编译sql
		String sql1 = "UPDATE account SET money=money-1000 WHERE accountName='张三'";
		String sql2 = "UPDATE account SET money=money+1000 WHERE accountName='李四'";
		// 执行预编译指令
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sql1);
		// 发送sql语句，执行sql语句，得到返回结果
		statement.executeUpdate();
		statement = connection.prepareStatement(sql2);
		// 发送sql语句，执行sql语句，得到返回结果
		statement.executeUpdate();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// 转账，使用事务
	public void test2() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = null;
		java.sql.PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(urlString, userString,
					passwd);
			connection.setAutoCommit(false);// 使用手动提交事务
			// 准备预编译sql
			String sql1 = "UPDATE account SET money=money-1000 WHERE accountName='张三'";
			String sql2 = "UPDATE account SET money=money+1000 WHERE accountName='李四'";
			// 执行预编译指令
			statement = connection.prepareStatement(sql1);
			// 发送sql语句，执行sql语句，得到返回结果
			statement.executeUpdate();
			statement = connection.prepareStatement(sql2);
			// 发送sql语句，执行sql语句，得到返回结果
			statement.executeUpdate();
		} catch (SQLException e) {
			connection.rollback();// 出现异常，回滚
		} finally {
			connection.commit();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

	}

	@Test
	// 转账，使用事务,回滚到指定代码段
	public void test3() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = null;
		java.sql.PreparedStatement statement = null;
		Savepoint sp = null;
		try {
			connection = DriverManager.getConnection(urlString, userString,
					passwd);
			connection.setAutoCommit(false);// 使用手动提交事务
			// 准备预编译sql
			String sql1 = "UPDATE account SET money=money-1000 WHERE accountName='张三'";
			String sql2 = "UPDATE account SET money=money+1000 WHERE accountName='李四'";
			// 执行预编译指令
			statement = connection.prepareStatement(sql1);
			statement.executeUpdate();
			statement = connection.prepareStatement(sql2);
			statement.executeUpdate();

			sp = connection.setSavepoint();

			statement = connection.prepareStatement(sql1);
			statement.executeUpdate();
			statement = connection.prepareStatement(sql2);
			statement.executeUpdate();

		} catch (SQLException e) {
			connection.rollback(sp);// 出现异常，回滚到指定代码段
		} finally {
			connection.commit();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

	}

	@Test
	// 批处理
	public void test4() throws ClassNotFoundException, SQLException {
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
		for (int i = 1; i <= 20; i++) {
			statement.setString(1, "王小" + i);
			if (i % 2 == 0)
				statement.setString(2, "男");
			else
				statement.setString(2, "女");
			statement.addBatch();
			if (i % 5 == 0) {
				statement.executeBatch();
				statement.clearBatch();
			}
		}
		statement.executeBatch();
		statement.clearBatch();
		// 发送sql语句，执行sql语句，得到返回结果
		statement.executeUpdate();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}
}
