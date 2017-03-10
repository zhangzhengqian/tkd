package com.test;

import java.math.BigDecimal;

public class TestBigdecimal {
	public static void main(String[] args) {
		String amount = "";
		Integer amount1 = 1000;
		amount = new BigDecimal(amount1).divide(new BigDecimal(100)).negate().toString();
		System.out.println(amount);
	}
}
