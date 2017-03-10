package com.tutorialspoint;

import java.util.Calendar;
import java.util.Date;

import com.utils.DateUtils;

public class TestDate {
	public static void main(String[] args) {
		Date date = new Date();
        String d = DateUtils.formatDate(date, "yyyy-MM-dd HH");
        //Calendar c = Calendar.getInstance();
        System.out.println(d);
	}
}
