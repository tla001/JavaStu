package cn.tla001.xpath;

import java.io.File;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Demo3 {
	public static void main(String[] args) throws DocumentException {
		Document doc = new SAXReader().read(new File("./src/user.xml"));
		Scanner in = new Scanner(System.in);
		System.out.println("please input your name:");
		String name = in.nextLine();
		System.out.println("please input your password:");
		String password = in.nextLine();

		Element userElem = (Element) doc.selectSingleNode("//user[@name='"
				+ name + "' and @password='" + password + "']");
		if (userElem != null) {
			System.out.println("successed!");
		} else {
			System.out.println("failture!");
		}
	}
}
