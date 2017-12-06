package cn.tla001.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionDemo {

	public static void main(String[] args) {
		Collection c1 = new ArrayList();
		Collection c2 = new ArrayList();

		show(c1, c2);
	}

	public static void show(Collection c1, Collection c2) {
		/*
		 * 添加元素
		 */
		c1.add("a");
		c1.add("b");
		c1.add("c");
		System.out.println(c1);

		c2.add("e");
		c2.add("d");
		c2.add("f");

		c1.addAll(c2);
		System.out.println(c1);

		/*
		 * 遍历元素
		 */
		Iterator ite = c2.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
		for (Object obj : c1) {
			System.out.println(obj);
		}

		/*
		 * 删除元素
		 */
		c1.removeAll(c2);
		if (c1.contains("a")) {
			c1.remove("a");
		}
		System.out.println(c1);
	}
}
