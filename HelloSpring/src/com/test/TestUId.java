package com.test;

public class TestUId {
	public static String get() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}
	public static void main(String[] args) {
		System.out.println(TestUId.get());
	}
	/*public static void main(String[] args) {
		System.out.println("进入");
		if((2==0)||(2==1)){
			System.out.println("111");
		}
	}*/
}
