package cn.tla001.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class JDBCDemo4 {
	private String url;
	private String user;
	private String passwd;
	private String driverClass;
	static {

	}

	@Test
	public void test1() throws IOException, SQLException,
			ClassNotFoundException {
		// 读取配置文件
		Properties properties = new Properties();
		FileInputStream inputStream = new FileInputStream(
				"./resources/db.properties");
		// 加载文件
		properties.load(inputStream);
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		passwd = properties.getProperty("passwd");
		driverClass = properties.getProperty("driverClass");

		Class.forName(driverClass);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(url, user, passwd);
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
