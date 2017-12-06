package cn.tla001.xmlst;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XmlDemo2 {
	@Test
	public void test() throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./resources/contact.xml"));
		StringBuffer sb = new StringBuffer();

		Element rootElem = doc.getRootElement();
		getChildElement(rootElem, sb);
		System.out.println(sb.toString());
	}

	private void getChildElement(Element elem, StringBuffer sb) {
		System.out.println(elem.getName());
		sb.append("<" + elem.getName());
		// �õ���ǩ�б�����
		List<Attribute> attrs = elem.attributes();
		if (attrs != null) {
			for (Attribute attr : attrs) {
				// System.out.println(attr.getName()+"="+attr.getValue());
				sb.append(" " + attr.getName() + "=\"" + attr.getValue() + "\"");
			}
		}
		sb.append(">");
		// �õ��ı�
		// String content = elem.getText();
		// System.out.println(content);
		Iterator<Node> it = elem.nodeIterator();
		while (it.hasNext()) {
			Node node = it.next();
			// ��ǩ
			if (node instanceof Element) {
				Element el = (Element) node;
				getChildElement(el, sb);
			}
			// �ı�
			if (node instanceof Text) {
				Text text = (Text) node;
				sb.append(text.getText());
			}
		}
		// ������ǩ
		sb.append("</" + elem.getName() + ">");
	}
}
