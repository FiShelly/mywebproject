package com.lspro.test;

import java.util.Calendar;

public class MethodTest {
	public static void main(String[] args) {
		String da[] = getDate(null,null);
		for(String str : da) {
			System.out.println(str);
		}
	}
	
	public static String[] getDate(String month,String year){
		String[] date = new String[2];
		Calendar cl = Calendar.getInstance();
		if(month == null || year == null){
			int yyyy = cl.get(Calendar.YEAR);
			int mm = cl.get(Calendar.MONTH)+1;
			int day = cl.get(Calendar.DATE);
			date[0] = yyyy+"-"+(mm>9?mm:"0"+mm)+"-01";
			date[1] = yyyy+"-"+(mm>9?mm:"0"+mm)+"-"+(day>9?day:"0"+day);
		} else {
			cl.set(Calendar.YEAR,Integer.parseInt(year));
			cl.set(Calendar.MARCH, Integer.parseInt(month)-1);
			int maxDay = cl.getActualMaximum(Calendar.DATE);
			date[0] = year+"-"+month+"-01";
			date[1] = year+"-"+month+"-"+maxDay;
		}
		return date;
	}
}
