package cn.tla001.xmlstw;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Demo1 {
	public static void main(String[] args) throws Exception{
		//一、读取文档
		Document doc=new SAXReader().read(new File("./src/contact.xml"));
		//二、修改
		
		//三、写进文档
		FileOutputStream out=new FileOutputStream("./src/temp.xml");
		//创建写出对象
		XMLWriter writer=new XMLWriter(out);
		//写出对象
		writer.write(doc);
		writer.close();
	}
}
