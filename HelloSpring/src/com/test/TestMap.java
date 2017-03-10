package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.entity.Student;

public class TestMap {
	/**
	 * <鍔熻兘堪><鍔熻兘鍏蜂綋瀹炵幇>
	 *
	 * @create锛�016骞�2鏈�5鏃�涓婂崍11:09:02
	 * @author锛歾zq
	 * @param args
	
	 */
	public static void main(String[] args) {
		Integer a = new Integer(1);
		Integer b = new Integer(1);
		System.out.println(a == b);
		Map<String,Student> results1 = new HashMap<String,Student>();
		Student s1 = new Student();
		Student s2 = new Student();
		String str1 = "1";
		String str2 = "1";
		System.out.println(str1.equals(str2));
		s1.setGrade("90");
		s2.setGrade("90");
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		results1.put("寮犱笁", s1);
		results1.put("寮犱笁", s1);
		System.out.println(s1.hashCode()+"**"+s2.hashCode());
		System.out.println(s1.equals(s2));
		for(Entry<String, Student> map : results1.entrySet()){
			System.out.println(map.getKey());
			System.out.println(map.getValue().getGrade());
			System.out.println(map.getClass().getName());
			System.out.println(map.hashCode());
			System.out.println();
	 }
		String s3=new String("zhaoxudong"); 
		String s4=new String("zhaoxudong"); 
		System.out.println(s3.hashCode()+"***"+s4.hashCode());
		
	}	
}
