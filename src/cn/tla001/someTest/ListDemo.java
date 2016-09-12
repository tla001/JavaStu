package cn.tla001.someTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class ListDemo {
	public static void main(String[] args) {
		ArrayList<String> list=new ArrayList<String>();
		list.add("tao");
		list.add("wang");
		list.add("zhang");
		//get 方式遍历 
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
		HashSet<String> hashSet=new HashSet<String>();
		hashSet.add("二狗蛋");
		hashSet.add("小黑");
		//迭代器遍历
		Iterator<String> iter=hashSet.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}	
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("zhang", "1");
		map.put("li", "2");
		Set<Entry<String,String>> entrys=map.entrySet();
		for(Entry<String,String> entry:entrys){
			System.out.println("key:"+entry.getKey()+"\t"+"value:"+entry.getValue());
		}
		
	}
}
