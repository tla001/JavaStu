package cn.tla001.jsonst;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class JSONDemo1 {
	@Test
	// JSONObject
	public void test1() {
		JSONObject jsonboj = new JSONObject("{'name':'wang','age':20}");
		String name = jsonboj.getString("name");
		int age = jsonboj.getInt("age");
		System.out.println(name + ":" + age);
	}

	@Test
	// JSONArray
	public void test2() {
		JSONArray jsonarr = new JSONArray(
				"[{'name':'tao','age':20},{'name':'wang','age':23}]");
		for (int i = 0; i < jsonarr.length(); i++) {
			String name = jsonarr.getJSONObject(i).getString("name");
			int age = jsonarr.getJSONObject(i).getInt("age");
			System.out.println(name + ":" + age);
		}
	}

	@Test
	// JSONObject JSONArray
	public void test3() {
		String str = "{'name':'xiazdong','age':20,'book':['book1','book2']}";
		JSONObject obj = new JSONObject(str);
		System.out.println(obj.getJSONArray("book").getString(0));
	}
}
