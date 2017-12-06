package cn.tla001.collection;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ListTest {
	public static void main(String[] args) {
		// listTest();
		vectorTest();
	}

	public static void listTest() {
		List list = new ArrayList();

		/*
		 * 添加
		 */
		list.add("a");
		list.add("b");
		list.add("c");

		list.add(1, "d");
		System.out.println(list);

		/*
		 * 删除
		 */
		list.remove(1);
		list.remove("c");
		System.out.println(list);

		/*
		 * 修改
		 */
		list.set(0, "ag ");
		System.out.println(list.get(0));

		System.out.println(list.subList(0, 1));

		System.out.println(list.indexOf("b"));

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public static void vectorTest() {
		Vector v = new Vector();

		v.addElement("a");
		v.addElement("b");
		v.addElement("c");

		Enumeration en = v.elements();
		while (en.hasMoreElements()) {
			System.out.println(en.nextElement());
		}

		Iterator ite = v.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
	}

	public static void linkListTest() {

		/*
		 * addFirst(); addLast(): jdk1.6 offerFirst(); offetLast();
		 * 
		 * 
		 * getFirst();.//获取但不移除，如果链表为空，抛出NoSuchElementException. getLast();
		 * jdk1.6 peekFirst();//获取但不移除，如果链表为空，返回null. peekLast():
		 * 
		 * 
		 * removeFirst();//获取并移除，如果链表为空，抛出NoSuchElementException. removeLast();
		 * jdk1.6 pollFirst();//获取并移除，如果链表为空，返回null. pollLast();
		 */
		LinkedList link = new LinkedList();
		link.addFirst("a");
		link.addFirst("b");
		link.addFirst("c");

		System.out.println(link.getFirst());
		System.out.println(link.getLast());
		System.out.println(link.peekFirst());
		System.out.println(link.peekLast());

	}
}
