package cn.tla001.xmlstw;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Demo1 {
	public static void main(String[] args) throws Exception{
		//һ����ȡ�ĵ�
		Document doc=new SAXReader().read(new File("./src/contact.xml"));
		//�����޸�
		
		//����д���ĵ�
		FileOutputStream out=new FileOutputStream("./src/temp.xml");
		//����д������
		XMLWriter writer=new XMLWriter(out);
		//д������
		writer.write(doc);
		writer.close();
	}
}
