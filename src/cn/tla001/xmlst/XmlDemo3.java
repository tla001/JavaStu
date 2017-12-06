package cn.tla001.xmlst;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlDemo3 {
	public static void main(String[] args) throws Exception {
		List<Contact> list = new ArrayList<Contact>();

		// ��ȡxml����װ����
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./resources/contact.xml"));
		// ��ȡcontact��ǩ
		Iterator<Element> it = doc.getRootElement().elementIterator("contact");
		while (it.hasNext()) {
			Element elem = it.next();
			// ����Contact
			Contact contact = new Contact();
			contact.setId(elem.attributeValue("id"));
			contact.setName(elem.elementText("name"));
			contact.setAge(elem.elementText("age"));
			contact.setPhone(elem.elementText("phone"));
			contact.setEmail(elem.elementText("email"));
			contact.setQq(elem.elementText("qq"));
			list.add(contact);
		}

		for (Contact contact : list) {
			System.out.println(contact);
		}
	}
}
