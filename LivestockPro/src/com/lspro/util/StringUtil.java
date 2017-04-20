package com.lspro.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StringUtil {

	public static String toChangeAddress(String provience,String crity,String country){
		if(provience.equals("北京市")||provience.equals("天津市")||provience.equals("上海市")||provience.equals("重庆市")){
			return  provience + country;
		} else {
			return provience+crity+country;
		}
	}

	public static String[] getDate(String month,String year){
		String[] date = new String[3];
		Calendar cl = Calendar.getInstance();
		if (year != null && month == null ){
			int MaxYear = cl.get(Calendar.DAY_OF_YEAR); 
			date[0] = year+"-01-01";
			date[1] = year+"-12-31";
			date[2] = MaxYear+"";
		} else if(year != null && month != null ) {
			cl.set(Calendar.YEAR,Integer.parseInt(year));
			cl.set(Calendar.MARCH, Integer.parseInt(month)-1);
			int maxDay = cl.getActualMaximum(Calendar.DATE);
			int yyyy = cl.get(Calendar.YEAR);
			int mm = cl.get(Calendar.MONTH)+1;
			if(month.equals(mm+"") && year.equals(yyyy+"")){
				date[2] = cl.get(Calendar.DATE)+"";
			}else {
				date[2] = maxDay+"";
			}
			date[0] = year+"-"+month+"-01";
			date[1] = year+"-"+month+"-"+maxDay;
		} else {
			int yyyy = cl.get(Calendar.YEAR);
			int mm = cl.get(Calendar.MONTH)+1;
			int day = cl.get(Calendar.DATE);
			date[0] = yyyy+"-"+(mm>9?mm:"0"+mm)+"-01";
			date[1] = yyyy+"-"+(mm>9?mm:"0"+mm)+"-"+(day>9?day:"0"+day);
			date[2] = day+"";
		}
		return date;
	}
	
	public static Integer toInitInteger(String adc){
		if(adc == null || adc.equals("")){
			return 0;
		} else {
			return Integer.parseInt(adc);
		}
	}
	
 	public static String getRadioButtonInit(String text){
		if( text == null|| text.equals("") ){
			return "否";
		}else {
			return text;
		}
	}
	
	public static String getWayString(String[] way) {
		String str = "";
		if (way != null) {
			for (String temp : way) {
				str = str + temp;
			}
		}
		return str;
	}
	
	public static List<String> getAnimalIdList(String[] tempAnimalId) {
		List<String> list = new ArrayList<String>();
		for(String temp : tempAnimalId){
			if(temp != null && !"".equals(temp)){
				list.add(temp);
			}
		}
		return list;
	}
	
	public static String[] getDataInit(String start_date,String end_date){
		String[] date = new String[2];
		if (start_date == null || "".equals(start_date)) {
			date[0] = "1900-01-01";
		} else {
			date[0] = start_date;
		}
		if (end_date == null || "".equals(end_date)) {
			date[1] = "2200-12-31";
		} else {
			date[1] = end_date;
		}
		return date;
	}
	
	public static String getInitString(String field){
		if (field == null) {
			return "";
		} else {
			return field;
		}
	}
	
	public static String getReturnDate(String date){
		if(date.equals("1900-01-01") || date.equals("2200-12-31")){
			return "";
		} else {
			return date;
		}
	}
	
	public static Integer[] getStringArr2intArr(String[] strArr){
		Integer[] intArr = new Integer[strArr.length];
		for(int i = 0;i<strArr.length;i++){
			intArr[i] = Integer.parseInt(strArr[i]);
		}
		return intArr;
	}
	
	public static String getCurrentDate(){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		return sim.format(new Date());
	}
	
}
