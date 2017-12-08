package cn.tla001.local;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class LocaleDemo2 {
	// 国际化货币
	@Test
	public void test1() {
		// 模拟语言环境
		Locale locale = Locale.CHINA;
		// 数据准备
		double number = 3000.0;
		// 工具类
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		// 国际化货币
		String str = nf.format(number);
		// 输出
		System.out.println(str);
	}

	// 计算 $100*10
	@Test
	public void test1_demo() throws ParseException {
		String str = "$100";
		int num = 10;

		// 1.分析是哪个国家的货币
		Locale locale = Locale.US;

		// 2.国际化工具类
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

		// 3.解析str
		Number number = nf.parse(str);

		// 4.进行计算
		int value = number.intValue() * num;

		// 5.格式化输出
		str = nf.format(value);
		System.out.println(str);
	}

	// 国际化数值
	@Test
	public void test2() {
		Locale locale = Locale.CHINA;
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		String str = nf.format(1000000);
		System.out.print(str);
	}

	// 国际化百分比
	@Test
	public void test3() {
		Locale locale = Locale.CHINA;
		NumberFormat nf = NumberFormat.getPercentInstance(locale);
		String str = nf.format(0.325);
		System.out.print(str);
	}

	// 国际化日期
	@Test
	public void test4() {
		int dateStyle = DateFormat.FULL;
		int timeStyle = DateFormat.FULL;
		Locale locale = Locale.US;
		DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle,
				locale);
		String date = df.format(new Date());
		System.out.println(date);
	}

	@Test
	public void testDate() throws ParseException {
		String str = "09-11-28 上午10时25分39秒 CST";

		int dateStyle = DateFormat.SHORT;
		int timeStyle = DateFormat.FULL;
		Locale locale = Locale.CHINA;
		// 创建DateFormat工具类，国际化日期
		DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle,
				locale);
		Date date = df.parse(str);
		System.out.println(date);
	}

}
