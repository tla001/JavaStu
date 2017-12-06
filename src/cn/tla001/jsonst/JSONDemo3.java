package cn.tla001.jsonst;

import java.util.Iterator;

import org.json.JSONObject;
import org.junit.Test;

public class JSONDemo3 {
	@Test
	public void test1() {
		JSONObject jo = new JSONObject("{'name':'wang','age':20}");
		System.out.println(jo.getInt("age"));

		Iterator ite = jo.keys();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			System.out.println(key + ":" + jo.get(key));
		}
	}
}
