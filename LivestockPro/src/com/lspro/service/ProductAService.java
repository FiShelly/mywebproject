package com.lspro.service;

import java.util.List;

import com.lspro.pojo.ProductA;

public interface ProductAService extends ServiceInter<ProductA> {
	public List<ProductA> findAll(String role,String loc, String id, String shipperName,
			String date1, String date2, String productName, String[] way,
			int currentPage, int lineSize);
	
	public double findCount(String address,String date1,String date2);

}
