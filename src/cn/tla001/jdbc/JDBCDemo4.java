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
		// ��ȡ�����ļ�
		Properties properties = new Properties();
		FileInputStream inputStream = new FileInputStream(
				"./resources/db.properties");
		// �����ļ�
		properties.load(inputStream);
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		passwd = properties.getProperty("passwd");
		driverClass = properties.getProperty("driverClass");

		Class.forName(driverClass);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(url, user, passwd);
		// ׼��Ԥ����sql
		String sqlString = "call pro_findByid2(?,?)";
		// ִ��Ԥ����ָ��
		java.sql.CallableStatement statement = connection
				.prepareCall(sqlString);
		// ���ò���
		statement.setInt(1, 1);
		// �����������
		statement.registerOutParameter(2, java.sql.Types.VARCHAR);
		// ����sql��䣬ִ��sql��䣬�õ����ؽ��
		ResultSet rSet = statement.executeQuery();
		while (rSet.next()) {
			// ����������
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
