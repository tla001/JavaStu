package cn.tla001.xmlst;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XmlDemo {
	public static void main(String[] args) {
		try {
			// 创建解析器
			SAXReader reader = new SAXReader();
			// 读取xml文档
			Document doc = reader.read(new File("./resources/contact.xml"));
			System.out.println(doc);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		//
	}
}
