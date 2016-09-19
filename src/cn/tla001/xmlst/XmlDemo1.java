package cn.tla001.xmlst;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XmlDemo1 {
	@Test
	public void test1() throws DocumentException {
		// ��ȡxml�ļ�
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./resources/contact.xml"));
		// System.out.println(doc);
		// �õ���ǰ�ڵ��µ������ӽڵ����
		Iterator<Node> item = doc.nodeIterator();
		while (item.hasNext()) {
			Node node = item.next();
			String name = node.getName();
			System.out.println(name);
			// ����ȥ����������ӽڵ㣬ֻ�б�ǩ�ڵ�����ӽڵ�
			// �жϵ�ǰ�ڵ��Ƿ��Ǳ�ǩ�ڵ�
			if (node instanceof Element) {
				Element elem = (Element) node;
				Iterator<Node> item2 = elem.nodeIterator();
				while (item2.hasNext()) {
					Node node2 = item2.next();
					System.out.println(node2.getName());
				}
			}
		}
	}

	@Test
	public void test2() throws Exception {
		// ��ȡxml�ļ�
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./resources/contact.xml"));
		// �õ�����ǩ
		Element rootElem = doc.getRootElement();
		getChildNodes(rootElem);

		// ��ȡ�ı����Ȼ�ȡ��ǩ���ٻ�ȡ��ǩ�ϵ��ı���
		Element nameELem = doc.getRootElement().element("contact")
				.element("name");
		// 1. �õ��ı�
		String text = nameELem.getText();
		System.out.println(text);
		// 2. �õ�ָ���ӱ�ǩ�����ı�����
		String text2 = doc.getRootElement().element("contact")
				.elementText("phone");
		System.out.println(text2);

		Element contactElem = doc.getRootElement().element("contact");
		// 2.4 �õ��������Զ��󣬷��ص�����
		Iterator<Attribute> it = contactElem.attributeIterator();
		while (it.hasNext()) {
			Attribute attr = it.next();
			System.out.println(attr.getName() + "=" + attr.getValue());
		}
	}

	private void getChildNodes(Element elem) {
		System.out.println(elem.getName());
		Iterator<Node> item = elem.nodeIterator();
		while (item.hasNext()) {
			Node node = item.next();

			if (node instanceof Element) {
				Element el = (Element) node;
				getChildNodes(el);
			}
		}
	}
}
