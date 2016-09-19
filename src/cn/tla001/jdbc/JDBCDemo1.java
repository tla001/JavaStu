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
		// ������������
		Driver driver = new com.mysql.jdbc.Driver();
		// �����û���������
		Properties properties = new Properties();
		properties.setProperty("user", userString);
		properties.setProperty("passwd", passwd);

		// �������ݿ�
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
			System.out.println("�޷���������");
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
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ����statement����
		java.sql.Statement statement = connection.createStatement();
		// ׼��sql
		String sqlString = "create table contact(id int primary key auto_increment,name varchar(20),gender varchar(2))";
		// String sqlString = "drop table contact";
		// ����sql��䣬ִ��sql��䣬�õ����ؽ��
		int count = statement.executeUpdate(sqlString);
		// ���
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
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ����statement����
		java.sql.Statement statement = connection.createStatement();
		// ׼��sql
		String sqlString = "insert contact(name,gender) values('����Ѿ','Ů')";

		// ����sql��䣬ִ��sql��䣬�õ����ؽ��
		int count = statement.executeUpdate(sqlString);
		// ���
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
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ����statement����
		java.sql.Statement statement = connection.createStatement();
		// ׼��sql
		String sqlString = "select * from contact";

		// ����sql��䣬ִ��sql��䣬�õ����ؽ��
		ResultSet rSet = statement.executeQuery(sqlString);
		// �ƶ����
		while (rSet.next()) {
			// ��������ȡֵ
			// int id = rSet.getInt(1);
			// String name = rSet.getString(2);
			// String gender = rSet.getString(3);
			// System.out.println(id + " " + name + " " + gender);

			// ����������
			int id = rSet.getInt("id");
			String name = rSet.getString("name");
			String gender = rSet.getString("gender");
			System.out.println(id + " " + name + " " + gender);
		}
		// ���

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}
}
