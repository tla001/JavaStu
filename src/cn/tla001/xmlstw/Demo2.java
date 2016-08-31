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
		//指定写出格式
		//OutputFormat format=OutputFormat.createCompactFormat();//紧凑
		OutputFormat format=OutputFormat.createPrettyPrint();//漂亮
		//指定xml的编码，避免编码错误
		format.setEncoding("utf-8");
		//创建写出对象
		XMLWriter writer=new XMLWriter(out,format);
		//写出对象
		writer.write(doc);
		writer.close();
	}
}
