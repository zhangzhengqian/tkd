package com.tutorialspoint;

import java.util.UUID;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.utils.DateUtils;

public class MainApp {
	public static void main(String[] args) {
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("G:\\qzlspace4\\HelloSpring\\src\\beans.xml");
		System.out.println(context.containsBean("helloWorld")+"1");
		HelloWorld h= (HelloWorld)context.getBean("helloWorld");
		System.out.println(h.getMessage()+"2");
		System.out.println(h.getMessage2()+"3");
		context.registerShutdownHook();
		context.close();
		//TextEditor textEditor = (TextEditor)context.getBean("textEditor");
		//textEditor.spell();
	}
	
}
