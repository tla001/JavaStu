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
		// 读取xml文件
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./resources/contact.xml"));
		// System.out.println(doc);
		// 得到当前节点下的所有子节点对象
		Iterator<Node> item = doc.nodeIterator();
		while (item.hasNext()) {
			Node node = item.next();
			String name = node.getName();
			System.out.println(name);
			// 继续去除其下面的子节点，只有标签节点才有子节点
			// 判断当前节点是否是标签节点
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
		// 读取xml文件
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./resources/contact.xml"));
		// 得到根标签
		Element rootElem = doc.getRootElement();
		getChildNodes(rootElem);

		// 获取文本（先获取标签，再获取标签上的文本）
		Element nameELem = doc.getRootElement().element("contact")
				.element("name");
		// 1. 得到文本
		String text = nameELem.getText();
		System.out.println(text);
		// 2. 得到指定子标签名的文本内容
		String text2 = doc.getRootElement().element("contact")
				.elementText("phone");
		System.out.println(text2);

		Element contactElem = doc.getRootElement().element("contact");
		// 2.4 得到所有属性对象，返回迭代器
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
