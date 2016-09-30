package cn.tla001.beanutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

public class Demo1 {

	// javabean 的基本操作
	@Test
	public void test1() throws Exception {
		// 基本操作
		Admin admin = new Admin();
		// admin.setUserName("Jack");
		// admin.setPwd("999");

		// beanutils 实现对象属性的拷贝,基本数据类型，自动实现转换
		BeanUtils.copyProperty(admin, "userName", "jack");
		BeanUtils.setProperty(admin, "age", 18);
		System.out.println(admin);

		// 对象的拷贝
		Admin admin2 = new Admin();
		BeanUtils.copyProperties(admin2, admin);
		System.out.println(admin2);

		// map手机开，拷贝到对象中
		Admin admin3 = new Admin();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "zhang");
		map.put("age", 29);
		BeanUtils.populate(admin3, map);
		System.out.println(admin3);
	}

	// 2. 自定义日期类型转换器
	@Test
	public void test2() throws Exception {
		// 模拟表单数据
		String name = "jack";
		String age = "20";
		String birth = "1990-5-23";

		// 对象
		Admin admin = new Admin();

		// 注册日期类型转换器：1， 自定义的方式
		ConvertUtils.register(new Converter() {
			// 转换的内部实现方法，需要重写
			@Override
			public Object convert(Class type, Object value) {
				// 判断
				if (type != Date.class) {
					return null;
				}
				if (value == null || "".equals(value.toString().trim())) {
					return null;
				}
				try {
					// 字符串转换为日期
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					return sdf.parse(value.toString());
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		}, Date.class);

		// 把表单提交的数据，封装到对象中
		BeanUtils.copyProperty(admin, "userName", name);
		BeanUtils.copyProperty(admin, "age", age);
		BeanUtils.copyProperty(admin, "birth", birth);

		// ------ 测试------
		System.out.println(admin);
	}

	// 3. 使用提供的日期类型转换器工具类
	@Test
	public void test3() throws Exception {
		// 模拟表单数据
		String name = "userName";
		String age = "20";
		String birth = "1993-5-21";

		// 对象
		Admin admin = new Admin();

		// 注册日期类型转换器：2， 使用组件提供的转换器工具类
		ConvertUtils.register(new DateLocaleConverter(), Date.class);

		// 把表单提交的数据，封装到对象中
		BeanUtils.copyProperty(admin, "userName", name);
		BeanUtils.copyProperty(admin, "age", age);
		BeanUtils.copyProperty(admin, "birth", birth);

		// ------ 测试------
		System.out.println(admin);
	}
}
