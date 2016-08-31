package cn.tla001.xpath;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Demo2 {
	public static void main(String[] args) throws Exception {
		Document doc = new SAXReader().read(new File("./src/contact.xml"));
		String xpath = "/contactList";
		xpath = "/contactList/contact";// ����Ŀ¼
		xpath = "//contact/name";// ���Ŀ¼
		xpath = "//contact[@id]";// ����id���Ե�contact��ǩ
		xpath = "//contact[2]";// �ڶ���contact��ǩ
		xpath = "//contact[last()]";// ���һ��
		xpath = "//name[text()='����']";// ����nameΪ�����ı�ǩ

		List<Node> list = doc.selectNodes(xpath);
		for (Node node : list) {
			System.out.println(node.getName());
		}
	}
}
