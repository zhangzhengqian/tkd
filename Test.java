package com.lc.zy;

import java.util.HashMap;
import java.util.Map;

import com.lc.zy.common.util.FreeMarkerUtils;

public class Test {

	public static void main(String[] args) {
		Map<String,Object> root = new HashMap<String,Object>();
		root.put("orderid", "foo-bar");
		String sss = new FreeMarkerUtils("/template/refund_message.ftl",root).getText();
		System.out.println(sss);
	}
}
