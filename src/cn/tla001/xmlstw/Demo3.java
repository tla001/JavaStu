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
 * �޸�xml���� ���ӣ���ǩ������ �޸ģ�����ֵ���ı� ɾ������ǩ������ֵ
 * 
 * @author Administrator
 * 
 */
public class Demo3 {
	@Test
	public void test1() throws Exception {
		// 1�����ĵ�
		Document doc = DocumentHelper.createDocument();
		// 2���ӱ�ǩ
		Element rootElem = doc.addElement("contactlist");
		Element contactElem = rootElem.addElement("contact");
		contactElem.addElement("name");
		// ��������
		contactElem.addAttribute("id", "001");
		contactElem.addAttribute("name", "tao");
		// ����д���ĵ�
		FileOutputStream out = new FileOutputStream("./resources/temp.xml");
		// ָ��д����ʽ
		// OutputFormat format=OutputFormat.createCompactFormat();//����
		OutputFormat format = OutputFormat.createPrettyPrint();// Ư��
		// ָ��xml�ı��룬����������
		format.setEncoding("utf-8");
		// ����д������
		XMLWriter writer = new XMLWriter(out, format);
		// д������
		writer.write(doc);
		writer.close();
	}

	@Test
	public void test2() throws Exception {
		// һ����ȡ�ĵ�
		Document doc = new SAXReader()
				.read(new File("./resources/contact.xml"));
		// �����޸� ����ֵ���ı�
		// �õ���ǩ
		Element contactElem = doc.getRootElement().element("contact");
		// �õ����Զ���
		Attribute idAttr = contactElem.attribute("id");
		// �޸�����ֵ
		idAttr.setValue("005");

		Element nameElem = contactElem.element("name");
		nameElem.setText("abd");

		// ����д���ĵ�
		FileOutputStream out = new FileOutputStream("./resources/temp.xml");
		// ָ��д����ʽ
		// OutputFormat format=OutputFormat.createCompactFormat();//����
		OutputFormat format = OutputFormat.createPrettyPrint();// Ư��
		// ָ��xml�ı��룬����������
		format.setEncoding("utf-8");
		// ����д������
		XMLWriter writer = new XMLWriter(out, format);
		// д������
		writer.write(doc);
		writer.close();
	}

	@Test
	public void test3() throws Exception {
		// һ����ȡ�ĵ�
		Document doc = new SAXReader()
				.read(new File("./resources/contact.xml"));
		// ����ɾ����ǩ
		// �õ���ǩ
		Element contactElem = doc.getRootElement().element("contact");
		Element nameElem = contactElem.element("name");
		nameElem.detach();
		// ɾ������
		// �õ��ڶ���contact��ǩ
		Element contactElem2 = (Element) doc.getRootElement().elements().get(1);
		// �õ�����
		Attribute attr = contactElem2.attribute("id");
		attr.detach();

		// ����д���ĵ�
		FileOutputStream out = new FileOutputStream("./resources/temp.xml");
		// ָ��д����ʽ
		// OutputFormat format=OutputFormat.createCompactFormat();//����
		OutputFormat format = OutputFormat.createPrettyPrint();// Ư��
		// ָ��xml�ı��룬����������
		format.setEncoding("utf-8");
		// ����д������
		XMLWriter writer = new XMLWriter(out, format);
		// д������
		writer.write(doc);
		writer.close();
	}
}
