package cn.tla001.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		mapTest();
	}

	public static void mapTest() {
		Map<Integer, String> m = new HashMap<Integer, String>();

		/*
		 * 1����ӡ� value put(key,value):����ǰһ����key������ֵ�����û�з���null.
		 * 
		 * 2��ɾ���� void clear():���map���ϡ� value remove(key):����ָ����key���������ֵ�ԡ�
		 * 
		 * 3���жϡ� boolean containsKey(key): boolean containsValue(value): boolean
		 * isEmpty();
		 * 
		 * 4����ȡ�� value get(key):ͨ������ȡֵ�����û�иü�����null�� ��Ȼ����ͨ������null�����ж��Ƿ����ָ������
		 * int size(): ��ȡ��ֵ�Եĸ�����
		 * 
		 * 5.���ⷽ���� Collection<V> values() ������ֵ�� Collection ��ͼ��
		 */
		m.put(1, "hello");
		m.put(2, "world");

		Collection<String> values = m.values();
		Iterator<String> it = values.iterator();
		while (it.hasNext()) {
			String value = it.next();
			System.out.println(value);
		}

		Set<Integer> keySet = m.keySet();
		Iterator<Integer> ite = keySet.iterator();
		while (ite.hasNext()) {
			Integer key = ite.next();
			String value = m.get(key);
			System.out.println(key + ": " + value);
		}

		Set<Map.Entry<Integer, String>> entrySet = m.entrySet();
		Iterator<Map.Entry<Integer, String>> ite1 = entrySet.iterator();
		while (ite1.hasNext()) {
			Map.Entry<Integer, String> entryMap = ite1.next();
			Integer key = entryMap.getKey();
			String value = entryMap.getValue();
			System.out.println(key + ":" + value);
		}
	}
}
