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
	// 查询， 自定义结果集封装数据
	public void test1() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备sql
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
	// BeanHandler: 查询返回单个对象
	public void test2() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备sql
		String sqlString = "select * from contact where id=?";
		QueryRunner qr = new QueryRunner();
		// 查询返回单个对象
		Contact contact = qr.query(connection, sqlString,
				new BeanHandler<Contact>(Contact.class), 11);
		System.out.println(contact);
		if (connection != null)
			connection.close();
	}

	@Test
	// BeanListHandler: 查询返回list集合，集合元素是指定的对象
	public void test3() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备sql
		String sqlString = "select * from contact";
		QueryRunner qr = new QueryRunner();
		// 查询返回多个对象
		java.util.List<Contact> contact = qr.query(connection, sqlString,
				new BeanListHandler<Contact>(Contact.class));
		System.out.println(contact);
		if (connection != null)
			connection.close();
	}

	@Test
	// 3) ArrayHandler, 查询返回结果记录的第一行，封装对对象数组, 即返回：Object[]
	// 4) ArrayListHandler, 把查询的每一行都封装为对象数组，再添加到list集合中
	// 5) ScalarHandler 查询返回结果记录的第一行的第一列 (在聚合函数统计的时候用)
	// 6) MapHandler 查询返回结果的第一条记录封装为map
	public void test4() throws ClassNotFoundException, SQLException {
		// 注册驱动
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		// 获取连接对象
		Connection connection = DriverManager.getConnection(urlString,
				userString, passwd);
		// 准备sql
		String sqlString = "select * from contact";
		QueryRunner qr = new QueryRunner();
		// 查询
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
