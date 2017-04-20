package com.lspro.service;

import java.util.List;



import com.lspro.pojo.ImmuneRecord;


public interface ImmuneRecordService extends ServiceInter<ImmuneRecord>{
	public List<ImmuneRecord> findAll(String farmId,String keyWord, int currentPage, int lineSize,String date1,String date2)throws Exception;
	public double findImmRate(String farmId,String date1, String date2);
}
