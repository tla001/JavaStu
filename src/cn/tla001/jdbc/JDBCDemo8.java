package cn.tla001.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.junit.Test;

public class JDBCDemo8 {
	private String urlString = "jdbc:mysql://localhost:3306/javastu";
	private String userString = "root";
	private String passwd = "root";

	@Test
	// ��ѯ�� �Զ���������װ����
	public void test1() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ׼��sql
		String sqlString = "select * from contact where id=?";
		QueryRunner qr = new QueryRunner();
		Contact contact = qr.query(connection, sqlString,
				new ResultSetHandler<Contact>() {
					public Contact handle(ResultSet rSet) throws SQLException {
						if (rSet.next()) {
							Contact contact = new Contact();
							contact.setId(rSet.getInt("id"));
							contact.setName(rSet.getString("name"));
							contact.setGender(rSet.getString("gender"));
							return contact;
						}
						return null;
					}
				}, 11);
		System.out.println(contact);
		if (connection != null)
			connection.close();
	}

	@Test
	// BeanHandler: ��ѯ���ص�������
	public void test2() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ׼��sql
		String sqlString = "select * from contact where id=?";
		QueryRunner qr = new QueryRunner();
		// ��ѯ���ص�������
		Contact contact = qr.query(connection, sqlString,
				new BeanHandler<Contact>(Contact.class), 11);
		System.out.println(contact);
		if (connection != null)
			connection.close();
	}

	@Test
	// BeanListHandler: ��ѯ����list���ϣ�����Ԫ����ָ���Ķ���
	public void test3() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ׼��sql
		String sqlString = "select * from contact";
		QueryRunner qr = new QueryRunner();
		// ��ѯ���ض������
		java.util.List<Contact> contact = qr.query(connection, sqlString,
				new BeanListHandler<Contact>(Contact.class));
		System.out.println(contact);
		if (connection != null)
			connection.close();
	}

	@Test
	// 3) ArrayHandler, ��ѯ���ؽ����¼�ĵ�һ�У���װ�Զ�������, �����أ�Object[]
	// 4) ArrayListHandler, �Ѳ�ѯ��ÿһ�ж���װΪ�������飬����ӵ�list������
	// 5) ScalarHandler ��ѯ���ؽ����¼�ĵ�һ�еĵ�һ�� (�ھۺϺ���ͳ�Ƶ�ʱ����)
	// 6) MapHandler ��ѯ���ؽ���ĵ�һ����¼��װΪmap
	public void test4() throws ClassNotFoundException, SQLException {
		// ע������
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// ��ȡ���Ӷ���
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// ׼��sql
		String sqlString = "select * from contact";
		QueryRunner qr = new QueryRunner();
		// ��ѯ
		// Object[] obj = qr.query(conn, sql, new ArrayHandler());
		// List<Object[]> list = qr.query(conn, sql, new ArrayListHandler());
		// Long num = qr.query(conn, sql, new ScalarHandler<Long>());
		Map<String, Object> map = qr.query(connection, sqlString,
				new MapHandler());
		// System.out.println(contact);
		if (connection != null)
			connection.close();
	}
}
