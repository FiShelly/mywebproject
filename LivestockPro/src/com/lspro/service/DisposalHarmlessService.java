package com.lspro.service;

import java.util.List;

import com.lspro.pojo.DisposalHarmless;


public interface DisposalHarmlessService extends ServiceInter<DisposalHarmless> {
	public List<DisposalHarmless> findAll(String farmId,String keyWord, int currentPage, int lineSize,String date1,String date2)throws Exception;
	
//	public double findDeathAllNum(String farmId,String date1,String date2);
}
