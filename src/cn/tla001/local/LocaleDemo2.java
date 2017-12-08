package cn.tla001.local;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class LocaleDemo2 {
	// ���ʻ�����
	@Test
	public void test1() {
		// ģ�����Ի���
		Locale locale = Locale.CHINA;
		// ����׼��
		double number = 3000.0;
		// ������
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		// ���ʻ�����
		String str = nf.format(number);
		// ���
		System.out.println(str);
	}

	// ���� $100*10
	@Test
	public void test1_demo() throws ParseException {
		String str = "$100";
		int num = 10;

		// 1.�������ĸ����ҵĻ���
		Locale locale = Locale.US;

		// 2.���ʻ�������
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

		// 3.����str
		Number number = nf.parse(str);

		// 4.���м���
		int value = number.intValue() * num;

		// 5.��ʽ�����
		str = nf.format(value);
		System.out.println(str);
	}

	// ���ʻ���ֵ
	@Test
	public void test2() {
		Locale locale = Locale.CHINA;
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		String str = nf.format(1000000);
		System.out.print(str);
	}

	// ���ʻ��ٷֱ�
	@Test
	public void test3() {
		Locale locale = Locale.CHINA;
		NumberFormat nf = NumberFormat.getPercentInstance(locale);
		String str = nf.format(0.325);
		System.out.print(str);
	}

	// ���ʻ�����
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
		String str = "09-11-28 ����10ʱ25��39�� CST";

		int dateStyle = DateFormat.SHORT;
		int timeStyle = DateFormat.FULL;
		Locale locale = Locale.CHINA;
		// ����DateFormat�����࣬���ʻ�����
		DateFormat df = DateFormat.getDateTimeInstance(dateStyle, timeStyle,
				locale);
		Date date = df.parse(str);
		System.out.println(date);
	}

}
