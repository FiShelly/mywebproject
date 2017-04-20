package com.lspro.service;

import java.util.List;

import com.lspro.pojo.ProductionRecords;

public interface ProductRecordService extends ServiceInter<ProductionRecords>{
	
	public List<ProductionRecords> findAll(String farmId,String keyWord, int currentPage, int lineSize,String date1,String date2)throws Exception;
	public double finddcr(String farmId,String date1,String date2);
}
