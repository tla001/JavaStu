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
		xpath = "/contactList/contact";// 绝对目录
		xpath = "//contact/name";// 相对目录
		xpath = "//contact[@id]";// 带有id属性的contact标签
		xpath = "//contact[2]";// 第二个contact标签
		xpath = "//contact[last()]";// 最后一个
		xpath = "//name[text()='张三']";// 返回name为张三的标签

		List<Node> list = doc.selectNodes(xpath);
		for (Node node : list) {
			System.out.println(node.getName());
		}
	}
}
