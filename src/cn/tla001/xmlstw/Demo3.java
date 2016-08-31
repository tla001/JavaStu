package cn.tla001.xmlstw;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

/**
 * 修改xml内容 增加：标签，属性 修改：属性值，文本 删除：标签，属性值
 * 
 * @author Administrator
 * 
 */
public class Demo3 {
	@Test
	public void test1() throws Exception {
		// 1创建文档
		Document doc = DocumentHelper.createDocument();
		// 2增加标签
		Element rootElem = doc.addElement("contactlist");
		Element contactElem = rootElem.addElement("contact");
		contactElem.addElement("name");
		// 增加属性
		contactElem.addAttribute("id", "001");
		contactElem.addAttribute("name", "tao");
		// 三、写进文档
		FileOutputStream out = new FileOutputStream("./src/temp.xml");
		// 指定写出格式
		// OutputFormat format=OutputFormat.createCompactFormat();//紧凑
		OutputFormat format = OutputFormat.createPrettyPrint();// 漂亮
		// 指定xml的编码，避免编码错误
		format.setEncoding("utf-8");
		// 创建写出对象
		XMLWriter writer = new XMLWriter(out, format);
		// 写出对象
		writer.write(doc);
		writer.close();
	}

	@Test
	public void test2() throws Exception {
		// 一、读取文档
		Document doc = new SAXReader().read(new File("./src/contact.xml"));
		// 二、修改 属性值，文本
		// 得到标签
		Element contactElem = doc.getRootElement().element("contact");
		// 得到属性对象
		Attribute idAttr = contactElem.attribute("id");
		// 修改属性值
		idAttr.setValue("005");

		Element nameElem = contactElem.element("name");
		nameElem.setText("abd");

		// 三、写进文档
		FileOutputStream out = new FileOutputStream("./src/temp.xml");
		// 指定写出格式
		// OutputFormat format=OutputFormat.createCompactFormat();//紧凑
		OutputFormat format = OutputFormat.createPrettyPrint();// 漂亮
		// 指定xml的编码，避免编码错误
		format.setEncoding("utf-8");
		// 创建写出对象
		XMLWriter writer = new XMLWriter(out, format);
		// 写出对象
		writer.write(doc);
		writer.close();
	}

	@Test
	public void test3() throws Exception {
		// 一、读取文档
		Document doc = new SAXReader().read(new File("./src/contact.xml"));
		// 二、删除标签
		// 得到标签
		Element contactElem = doc.getRootElement().element("contact");
		Element nameElem = contactElem.element("name");
		nameElem.detach();
		// 删除属性
		// 得到第二个contact标签
		Element contactElem2 = (Element) doc.getRootElement().elements().get(1);
		// 得到属性
		Attribute attr = contactElem2.attribute("id");
		attr.detach();

		// 三、写进文档
		FileOutputStream out = new FileOutputStream("./src/temp.xml");
		// 指定写出格式
		// OutputFormat format=OutputFormat.createCompactFormat();//紧凑
		OutputFormat format = OutputFormat.createPrettyPrint();// 漂亮
		// 指定xml的编码，避免编码错误
		format.setEncoding("utf-8");
		// 创建写出对象
		XMLWriter writer = new XMLWriter(out, format);
		// 写出对象
		writer.write(doc);
		writer.close();
	}
}
