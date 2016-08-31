package cn.tla001.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Demo1 {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		// 创建SAXParser对象
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		// 调用parser方法
		/**
		 * 参数一：文件 参数二：DefaultHandler子类
		 */
		parser.parse(new File("./src/contact.xml"), new MyDefaultHandler());
	}
}

class MyDefaultHandler extends DefaultHandler {
	/**
	 * 开始文档时调用
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		// super.startDocument();
		System.out.println("start document");
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		// super.endDocument();
		System.out.println("end document");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		// super.startElement(uri, localName, qName, attributes);
		System.out.println("start element-->" + qName);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		// super.endElement(uri, localName, qName);
		System.out.println("end element-->" + qName);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		// super.characters(ch, start, length);
		String text = new String(ch, start, length);
		System.out.println("start element-->" + text);
	}
}