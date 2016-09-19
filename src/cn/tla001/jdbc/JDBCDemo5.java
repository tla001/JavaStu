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
	// ת�ˣ�û��ʹ������
	public void test1() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ׼��Ԥ����sql
		String sql1 = "UPDATE account SET money=money-1000 WHERE accountName='����'";
		String sql2 = "UPDATE account SET money=money+1000 WHERE accountName='����'";
		// ִ��Ԥ����ָ��
		java.sql.PreparedStatement statement = connection
				.prepareStatement(sql1);
		// ����sql��䣬ִ��sql��䣬�õ����ؽ��
		statement.executeUpdate();
		statement = connection.prepareStatement(sql2);
		// ����sql��䣬ִ��sql��䣬�õ����ؽ��
		statement.executeUpdate();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}

	@Test
	// ת�ˣ�ʹ������
	public void test2() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = null;
		java.sql.PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(urlString, userString,
					passwd);
			connection.setAutoCommit(false);// ʹ���ֶ��ύ����
			// ׼��Ԥ����sql
			String sql1 = "UPDATE account SET money=money-1000 WHERE accountName='����'";
			String sql2 = "UPDATE account SET money=money+1000 WHERE accountName='����'";
			// ִ��Ԥ����ָ��
			statement = connection.prepareStatement(sql1);
			// ����sql��䣬ִ��sql��䣬�õ����ؽ��
			statement.executeUpdate();
			statement = connection.prepareStatement(sql2);
			// ����sql��䣬ִ��sql��䣬�õ����ؽ��
			statement.executeUpdate();
		} catch (SQLException e) {
			connection.rollback();// �����쳣���ع�
		} finally {
			connection.commit();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

	}

	@Test
	// ת�ˣ�ʹ������,�ع���ָ�������
	public void test3() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = null;
		java.sql.PreparedStatement statement = null;
		Savepoint sp = null;
		try {
			connection = DriverManager.getConnection(urlString, userString,
					passwd);
			connection.setAutoCommit(false);// ʹ���ֶ��ύ����
			// ׼��Ԥ����sql
			String sql1 = "UPDATE account SET money=money-1000 WHERE accountName='����'";
			String sql2 = "UPDATE account SET money=money+1000 WHERE accountName='����'";
			// ִ��Ԥ����ָ��
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
			connection.rollback(sp);// �����쳣���ع���ָ�������
		} finally {
			connection.commit();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}

	}

	@Test
	// ������
	public void test4() throws ClassNotFoundException, SQLException {
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
		for (int i = 1; i <= 20; i++) {
			statement.setString(1, "��С" + i);
			if (i % 2 == 0)
				statement.setString(2, "��");
			else
				statement.setString(2, "Ů");
			statement.addBatch();
			if (i % 5 == 0) {
				statement.executeBatch();
				statement.clearBatch();
			}
		}
		statement.executeBatch();
		statement.clearBatch();
		// ����sql��䣬ִ��sql��䣬�õ����ؽ��
		statement.executeUpdate();

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}
}
