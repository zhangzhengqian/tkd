package com.test;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.entity.Person;

public class TestSpring {

	public static void main(String[] args) {
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("G:\\qzlspace4\\HelloSpring\\src\\beans.xml");
		System.out.println(context.containsBean("person")+"1");
		Person p= (Person)context.getBean("person");
		p.buy();
		System.out.println(p.getName()+"2");
		System.out.println(p.getGrade()+"3");
		context.registerShutdownHook();
		context.close();
		//TextEditor textEditor = (TextEditor)context.getBean("textEditor");
		//textEditor.spell();
	}
	

}
