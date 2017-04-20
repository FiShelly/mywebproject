package com.lspro.dao.inter;

import java.util.List;

import com.lspro.pojo.EpidemicReport;

public interface IEpidemicReportDAO extends IDAO<EpidemicReport> {
	public List<EpidemicReport> findAllForUser(String hql, String farmId,
			String date1, String date2, String sickSpecies, int status,
			int currentPage, int lineSize);

	public List<EpidemicReport> findAllForAdmin(String hql, String adminLoc,String address,
			String date1, String date2, int status, int currentPage,
			int lineSize);

	public List<EpidemicReport> findAllLoc(String hql, String province,String city,String search);
	
	public void changeStatus(String hql,int id);

	public double findCount(String hql, String farmId, String date1,String date2, int status);
}
