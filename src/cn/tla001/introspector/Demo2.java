package cn.tla001.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.junit.Test;

/*
��ʡ--->һ����̬�ķ���.

��ʡ��Ҫ��� �����⣺ �Ѷ�����������ݷ�װ �������С�


 */
public class Demo2 {
	
	
	@Test
	public void getAllProperty() throws IntrospectionException{
		//Introspector ��ʡ��
		BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
		//ͨ��BeanInfo��ȡ���е�����������
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors(); //��ȡһ�����е���������������
		for(PropertyDescriptor p : descriptors){
			System.out.println(p.getReadMethod()); //get����
		}
		
		
	}
	
	
	@Test
	public  void testProperty() throws Exception{
		Person p = new Person();
		//����������
		PropertyDescriptor descriptor = new PropertyDescriptor("id", Person.class);
		//��ȡ���Զ�Ӧ��get������set�������û��߻�ȡ�����ˡ�
		Method  m = descriptor.getWriteMethod();  //��ȡ���Ե�set������
		//ִ�и÷�����������ֵ
		m.invoke(p,110);
		
		Method readMethod = descriptor.getReadMethod(); //�ǻ�ȡ���Ե�get����
		
		System.out.println(readMethod.invoke(p, null));
	}

}
