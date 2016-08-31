package cn.tla001.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Demo2 {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		MyDefaultHandle hd = new MyDefaultHandle();
		parser.parse(new File("./src/contact.xml"), hd);
		System.out.println(hd.getContent());
	}
}

class MyDefaultHandle extends DefaultHandler {
	private StringBuffer sb = new StringBuffer();

	public String getContent() {
		return sb.toString();
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

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		sb.append("<" + qName);
		if (attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String attrname = attributes.getQName(i);
				String attrvalue = attributes.getValue(i);
				sb.append(" " + attrname + "=\"" + attrvalue + "\"");
			}
		}
		sb.append(">");
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		sb.append("</" + qName + ">");
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String text = new String(ch, start, length);
		sb.append(text);
	}
}