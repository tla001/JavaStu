package cn.tla001.sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Demo3 {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		MyDefaultHandle1 hd = new MyDefaultHandle1();
		parser.parse(new File("./src/contact.xml"), hd);
		List<Contact> list = hd.getList();
		for (Contact contact : list) {
			System.out.println(contact);
		}
	}
}

class MyDefaultHandle1 extends DefaultHandler {
	private List<Contact> list = new ArrayList<Contact>();
	private Contact contact;

	public List<Contact> getList() {
		return list;
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	private String tag;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		if ("contact".equals(qName)) {
			contact = new Contact();
			contact.setId(attributes.getValue("id"));
		}
		tag = qName;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		if ("contact".equals(qName)) {
			list.add(contact);
		}
		tag = null;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String content = new String(ch, start, length);
		if ("age".equals(tag)) {
			contact.setAge(content);
		}
		if ("name".equals(tag)) {
			contact.setName(content);
		}
		if ("email".equals(tag)) {
			contact.setEmail(content);
		}
		if ("phone".equals(tag)) {
			contact.setPhone(content);
		}
		if ("qq".equals(tag)) {
			contact.setQq(content);
		}
	}
}