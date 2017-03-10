package com.tutorialspoint;

public class HelloWorld {
	private String message;
	
	private String message2;
	
	private String message3;
	
	private Message message4;
	
	public void sendMessage(){
		System.out.println("enter sendmessage method");
		message4.sendMessage();
	}
	public String getMessage2() {
		return message2;
	}

	public void setMessage2(String message2) {
		this.message2 = message2;
	}

	public String getMessage3() {
		return message3;
	}

	public void setMessage3(String message3) {
		this.message3 = message3;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void init(){
		System.out.println("bean will init");
	}
	public void destory(){
		System.out.println("bean will destory");
	}
	public Message getMessage4() {
		return message4;
	}
	public void setMessage4(Message message4) {
		this.message4 = message4;
	}
	public HelloWorld() {
		super();
		System.out.println("Constructor is building");
	}
	
	
}
