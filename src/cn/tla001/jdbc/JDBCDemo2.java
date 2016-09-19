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
	// ����
	public void test1() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ׼��Ԥ����sql
		String sqlString = "insert contact(name,gender) values(?,?)";
		// ִ��Ԥ����ָ��
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		// ���ò���
		statement.setString(1, "����");
		statement.setString(2, "��");
		// ����sql��䣬ִ��sql��䣬�õ����ؽ��
		statement.executeUpdate();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// �޸�
	public void test2() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ׼��Ԥ����sql
		String sqlString = "update contact set name=? where id=?";
		// ִ��Ԥ����ָ��
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		// ���ò���
		statement.setString(1, "����");
		statement.setInt(2, 3);
		// ����sql��䣬ִ��sql��䣬�õ����ؽ��
		statement.executeUpdate();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// ɾ��
	public void test3() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ׼��Ԥ����sql
		String sqlString = "delete from contact where id=?";
		// ִ��Ԥ����ָ��
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
		// ���ò���
		statement.setInt(1, 3);
		// ����sql��䣬ִ��sql��䣬�õ����ؽ��
		statement.executeUpdate();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// ��ѯ
	public void test4() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ׼��Ԥ����sql
		String sqlString = "select * from contact";
		// ִ��Ԥ����ָ��
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sqlString);
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
