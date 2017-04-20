package com.lspro.service;

import java.util.List;

import com.lspro.pojo.ProductB;

public interface ProductBService extends ServiceInter<ProductB> {
	public List<ProductB> findAll(String role,String loc, String id,
			String shipperName,String date1,String date2, String productName,
			int currentPage, int lineSize);
	
	public double findCount(String address,String date1,String date2);
}
