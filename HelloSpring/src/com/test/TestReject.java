package com.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.entity.Student;

public class TestReject {
	public static void main(String[] args) throws Exception{
		
		Class<?> class1 = null;
		
		try {
			class1 = Class.forName("com.entity.Student");
			
			//实例化默认构造方法
			Student student = (Student)class1.newInstance();
			
			student.setGrade("100");
			System.out.println(student.getGrade());
			
			//获取私有属性
			Field[] fields = class1.getFields();
			for(int i = 0;i<fields.length;i++){
				int mod = fields[i].getModifiers();
				System.out.println(mod);
				String priv = Modifier.toString(mod);
				System.out.println(priv+" "+fields[i].getType().getName()+" "+fields[i].getName());
			}
			
			Method[] methods = class1.getMethods();
			for(Method method : methods){
				System.out.println(method.getName());
			}
			
			Method m1 = class1.getMethod("reflect1");
			m1.invoke(class1.newInstance());
			
			Method m2 = class1.getMethod("reflect2",String.class,String.class);
			m2.invoke(class1.newInstance(), "1","2");
			
		} catch (ClassNotFoundException e) {
			
		}
	}
	
	
}
