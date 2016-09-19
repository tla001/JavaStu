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
	// ��ѯ
	public void test1() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
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
