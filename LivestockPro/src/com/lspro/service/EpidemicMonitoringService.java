package com.lspro.service;

import java.util.List;

import com.lspro.pojo.EpidemicMonitoring;

public interface EpidemicMonitoringService extends ServiceInter<EpidemicMonitoring>{
	public List<EpidemicMonitoring> findAll(String farmId,String keyWord, int currentPage, int lineSize,String date1,String date2) ;
	public double findCount(String farmId, String date1,String date2,int days);

}
