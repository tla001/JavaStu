package cn.tla001.local;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class LocalDemo1 {
	@Test
	public void test1() {
		// ���ػ�����:Locale
		// Locale locale = Locale.CHINA;
		// Locale locale = Locale.US;
		Locale locale = Locale.getDefault();
		String country = locale.getCountry();
		String displayCountry = locale.getDisplayCountry();
		String language = locale.getLanguage();
		String displayLanguage = locale.getDisplayLanguage();

		System.out.println(country);
		System.out.println(displayCountry);
		System.out.println(language);
		System.out.println(displayLanguage);
	}

	@Test
	public void test2() {
		Locale locale = Locale.US;
		// �������������ResourceBundle
		ResourceBundle bundle = ResourceBundle.getBundle("cn.tla001.local.msg",
				locale);

		// ����key��ȡ�����ļ��е�ֵ
		String username = bundle.getString("username");
		String pwd = bundle.getString("passwd");

		// ���
		System.out.println(username);
		System.out.println(pwd);
	}
}
