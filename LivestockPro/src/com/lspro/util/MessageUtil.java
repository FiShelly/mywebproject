package com.lspro.util;

import java.util.ResourceBundle;

public class MessageUtil {
	public static String get(String key){
		ResourceBundle rb = ResourceBundle.getBundle("com.lspro.util.Message") ; 
		return rb.getString(key) ;
	}
}
