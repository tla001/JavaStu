package cn.tla001.xmlstw;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Demo2 {
	public static void main(String[] args) throws Exception{
		Document doc=new SAXReader().read(new File("./src/contact.xml"));
		FileOutputStream out=new FileOutputStream("./src/temp.xml");
		//ָ��д����ʽ
		//OutputFormat format=OutputFormat.createCompactFormat();//����
		OutputFormat format=OutputFormat.createPrettyPrint();//Ư��
		//ָ��xml�ı��룬����������
		format.setEncoding("utf-8");
		//����д������
		XMLWriter writer=new XMLWriter(out,format);
		//д������
		writer.write(doc);
		writer.close();
	}
}
