package com.lspro.service;

import java.util.List;

import com.lspro.pojo.DisinfectionRecord;


public interface DisinfectionRecordService extends ServiceInter<DisinfectionRecord> {

	public List<DisinfectionRecord> findAll(String farmId,String keyWord, int currentPage, int lineSize,String date1,String date2);
	public double findCount(String farmId,String date1, String date2,int days);
}
