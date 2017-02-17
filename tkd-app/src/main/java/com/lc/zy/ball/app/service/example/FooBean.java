package com.lc.zy.ball.app.service.example;

public class FooBean {
	
	private String foo = null;
	private String hello = null;
	public String getFoo() {
		return foo;
	}
	public void setFoo(String foo) {
		this.foo = foo;
	}
	public String getHello() {
		return hello;
	}
	public void setHello(String hello) {
		this.hello = hello;
	}
	@Override
	public String toString() {
		return "FooBean [foo=" + foo + ", hello=" + hello + "]";
	}
	
}
