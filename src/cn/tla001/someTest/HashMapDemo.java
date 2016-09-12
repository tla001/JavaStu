package cn.tla001.someTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {
	public static void main(String[] args) {
		HashMap hm = new HashMap();
		hm.put("tao", new Double(3.14));
		hm.put("wang", new Double(55.1));
		hm.put("zhang", new Double(55.12));

		Set set = hm.entrySet();

		Iterator ite = set.iterator();
		while (ite.hasNext()) {
			Map.Entry<String, Double> me = (Map.Entry<String, Double>) ite
					.next();
			System.out.println(me.getKey() + ":");
			System.out.println(me.getValue());
		}
	}
}
