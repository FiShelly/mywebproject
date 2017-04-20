package com.teacherwork.util;

import java.util.ResourceBundle;

public class MessageUtil {
	public static String get(String key){
		ResourceBundle rb = ResourceBundle.getBundle("Message") ; 
		return rb.getString(key) ;
	}
}
