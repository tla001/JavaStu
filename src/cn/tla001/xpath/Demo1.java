package cn.tla001.xpath;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Demo1 {
	public static void main(String[] args) throws Exception {
		/**
		 * 删除id为2的学生标签
		 */
		Document doc = new SAXReader().read(new File("./src/contact.xml"));
		// 查询id为2
		Element contactElem = (Element) doc
				.selectSingleNode("//contact[@id='001']");
		// 删除标签
		contactElem.detach();

		FileOutputStream out = new FileOutputStream("./src/temp.xml");
		OutputFormat formate = OutputFormat.createPrettyPrint();
		formate.setEncoding("utf-8");

		XMLWriter writer = new XMLWriter(out, formate);
		writer.write(doc);
		writer.close();
	}
}
