package cn.tla001.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class JDBCDemo3 {
	private String urlString = "jdbc:mysql://localhost:3306/javastu";
	private String userString = "root";
	private String passwd = "root";

	@Test
	// 查询
	public void test1() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备预编译sql
		String sqlString = "call pro_findByid2(?,?)";
		// 执行预编译指令
		java.sql.CallableStatement statement = connection
				.prepareCall(sqlString);
		// 设置参数
		statement.setInt(1, 1);
		// 设置输出参数
		statement.registerOutParameter(2, java.sql.Types.VARCHAR);
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
