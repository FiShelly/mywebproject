package com.lspro.service;

import java.util.List;

import com.lspro.pojo.EpidemicReport;

public interface EpidemicReportService extends ServiceInter<EpidemicReport> {
	public List<EpidemicReport> findAllForUser(String farmId, String date1,
			String date2, String sickSpecies, int status, int currentPage,int lineSize);

	public List<EpidemicReport> findAllForAdmin(String adminLoc, String address,String date1,
			String date2, int status, int currentPage, int lineSiz);
	
	public List<EpidemicReport> findAllLoc(String province, String city,String search);
	
	public boolean changeStatus(int id);
	
	public double findCount(String farmId,String date1,String date2,int status); 

}
