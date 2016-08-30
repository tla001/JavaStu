package cn.tla001.introspector;

import java.io.BufferedReader;
/*
���� ��дһ�������������������ļ������ݣ������������ض�Ӧ�Ķ��󣬲��ҰѶ���Ҫ�ж�Ӧ������ֵ��
 */
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/*
 
 �Ժ����ǿ������ ��ʱ�������Ǿ�����Ҫ��һЩ���ݷ�װ�������еġ�
 
 
 
 */


public class Demo1 {
	
	public static void main(String[] args) throws Exception {
		Person p = (Person) getInstance();
		System.out.println(p);
		
	}
	
	//���������ļ���������������Ķ�����Ҫ�Ѷ��������ֵ��װ�������С�
	public static Object getInstance() throws Exception{
		BufferedReader bufferedReader = new BufferedReader(new FileReader("obj.txt"));
		String className =  bufferedReader.readLine(); //��ȡ�����ļ���ȡ��������������
		Class clazz = Class.forName(className);
		//ͨ��class�����ȡ���޲εĹ��췽��
		Constructor constructor = clazz.getConstructor(null);
		//��������
		Object o  = constructor.newInstance(null);
		//��ȡ����ֵ
		String line = null;
		while((line = bufferedReader.readLine())!=null){
			String[] datas = line.split("=");
			//ͨ����������ȡ����Ӧ��Field����
			Field field = 	clazz.getDeclaredField(datas[0]);
			if(field.getType()==int.class){
				field.set(o, Integer.parseInt(datas[1]));
			}else{
				field.set(o, datas[1]);
			}
			
		}
		return o;
		
	}

}
