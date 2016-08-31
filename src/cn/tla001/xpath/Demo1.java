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
		 * ɾ��idΪ2��ѧ����ǩ
		 */
		Document doc = new SAXReader().read(new File("./src/contact.xml"));
		// ��ѯidΪ2
		Element contactElem = (Element) doc
				.selectSingleNode("//contact[@id='001']");
		// ɾ����ǩ
		contactElem.detach();

		FileOutputStream out = new FileOutputStream("./src/temp.xml");
		OutputFormat formate = OutputFormat.createPrettyPrint();
		formate.setEncoding("utf-8");

		XMLWriter writer = new XMLWriter(out, formate);
		writer.write(doc);
		writer.close();
	}
}
