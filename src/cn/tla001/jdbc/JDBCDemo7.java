package cn.tla001.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

public class JDBCDemo7 {
	private String urlString = "jdbc:mysql://localhost:3306/javastu";
	private String userString = "root";
	private String passwd = "root";

	@Test
	// 数据库元数据
	public void test1() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 获取元数据
		DatabaseMetaData metaData = connection.getMetaData();

		System.out.println(metaData.getUserName());
		System.out.println(metaData.getURL());
		System.out.println(metaData.getDatabaseProductName());
		if (connection != null)
			connection.close();
	}

	@Test
	// 参数元数据
	public void test2() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备预编译sql
		String sqlString = "select * from contact where id=? and name=?";
		// 执行预编译指令
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		// 参数元数据
		ParameterMetaData parameterMetaData = statement.getParameterMetaData();
		int count = parameterMetaData.getParameterCount();
		System.out.println(count);

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// 结果元数据
	public void test3() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备预编译sql
		String sqlString = "select * from contact ";
		// 执行预编译指令
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		ResultSet rs = statement.executeQuery();
		// 得到接过集元数据
		ResultSetMetaData metaData = rs.getMetaData();
		while (rs.next()) {
			int count = metaData.getColumnCount();
			for (int i = 0; i < count; i++) {
				String columnNameString = metaData.getColumnName(i + 1);
				Object object = rs.getObject(columnNameString);
				System.out.println(columnNameString + "=" + object + ",");
			}
			System.out.println();
		}
		// 参数元数据
		ParameterMetaData parameterMetaData = statement.getParameterMetaData();
		int count = parameterMetaData.getParameterCount();
		System.out.println(count);

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}
}
