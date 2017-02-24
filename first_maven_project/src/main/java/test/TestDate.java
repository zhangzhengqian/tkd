package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.DateUtils;



public class TestDate {
	public static void main(String[] args) {
		Date date= DateUtils.parse("2027-12-12",null);
		System.out.println(date);
		System.out.println(date.getTime());
		Long l = (long) 14788800;
		Date newDate = new Date(l*100000);
		String newDateStr = DateUtils.formatDate(newDate, "yyyy-MM-dd HH:mm:ss");
		System.out.println(newDateStr);
		
		System.out.println(new Date());
		String dateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
		Date datePattern = DateUtils.parse(dateStr,null);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date da = df.parse(df.format(new Date()));
			System.out.println(da);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(datePattern);
		
	}
}
