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
	// ���ݿ�Ԫ����
	public void test1() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ��ȡԪ����
		DatabaseMetaData metaData = connection.getMetaData();

		System.out.println(metaData.getUserName());
		System.out.println(metaData.getURL());
		System.out.println(metaData.getDatabaseProductName());
		if (connection != null)
			connection.close();
	}

	@Test
	// ����Ԫ����
	public void test2() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ׼��Ԥ����sql
		String sqlString = "select * from contact where id=? and name=?";
		// ִ��Ԥ����ָ��
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		// ����Ԫ����
		ParameterMetaData parameterMetaData = statement.getParameterMetaData();
		int count = parameterMetaData.getParameterCount();
		System.out.println(count);

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// ���Ԫ����
	public void test3() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ׼��Ԥ����sql
		String sqlString = "select * from contact ";
		// ִ��Ԥ����ָ��
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		ResultSet rs = statement.executeQuery();
		// �õ��ӹ���Ԫ����
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
		// ����Ԫ����
		ParameterMetaData parameterMetaData = statement.getParameterMetaData();
		int count = parameterMetaData.getParameterCount();
		System.out.println(count);

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}
}
