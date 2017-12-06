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
		 * 1，添加。 value put(key,value):返回前一个和key关联的值，如果没有返回null.
		 * 
		 * 2，删除。 void clear():清空map集合。 value remove(key):根据指定的key翻出这个键值对。
		 * 
		 * 3，判断。 boolean containsKey(key): boolean containsValue(value): boolean
		 * isEmpty();
		 * 
		 * 4，获取。 value get(key):通过键获取值，如果没有该键返回null。 当然可以通过返回null，来判断是否包含指定键。
		 * int size(): 获取键值对的个数。
		 * 
		 * 5.特殊方法： Collection<V> values() 包含的值的 Collection 视图。
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
