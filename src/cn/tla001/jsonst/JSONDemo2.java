package cn.tla001.jsonst;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;
import org.junit.Test;

public class JSONDemo2 {
	@Test
	// JSONObject
	public void test1() throws FileNotFoundException {
		JSONStringer js = new JSONStringer();
		JSONObject obj2 = new JSONObject();
		JSONObject obj3 = new JSONObject();
		JSONObject obj4 = new JSONObject();
		obj4.put("title", "book1").put("price", "$11");
		obj3.put("book", obj4);
		obj3.put("author", new JSONObject().put("name", "author-1"));

		JSONObject obj5 = new JSONObject();
		JSONObject obj6 = new JSONObject();
		obj6.put("title", "book2").put("price", "$22");
		obj5.put("book", obj6);
		obj5.put("author", new JSONObject().put("name", "author-2"));

		JSONArray obj7 = new JSONArray();
		obj7.put(obj3).put(obj5);

		obj2.put("title", "BOOK");
		obj2.put("signing", obj7);

		js.object().key("session").value(obj2).endObject();

		System.out.println(js.toString());

		PrintWriter out = new PrintWriter(new FileOutputStream(
				"./resources/json1.txt"));
		out.println(js.toString());
	}

	@Test
	// JSONTokener是用来读取JSON格式的文件
	public void test3() throws JSONException, FileNotFoundException {
		JSONObject obj = new JSONObject(new JSONTokener(new FileReader(
				new File("./resources/json1.txt"))));
		System.out.println(obj.getJSONArray("book").getString(1)); // 可以读取book2
	}
}
