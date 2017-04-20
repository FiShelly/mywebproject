package com.lspro.service.impl;

import java.util.List;

import com.lspro.dao.inter.IEpidemicReportDAO;
import com.lspro.pojo.EpidemicReport;
import com.lspro.service.EpidemicReportService;

public class EpidemicReportServiceImpl extends ServiceImpl<EpidemicReport> implements EpidemicReportService {

	private IEpidemicReportDAO epiDao;
	
	@Override
	public List<EpidemicReport> findAllForUser(String farmId, String date1,
			String date2, String sickSpecies, int status, int currentPage,
			int lineSize) {
		String hql = "from EpidemicReport as epi where epi.farm.farmId = :farmId and epi.sickSpecies like :sickSpecies and epi.status = :status "
				+ "and epi.date between :date1 and :date2 order by epi.date desc";
		List<EpidemicReport> list = epiDao.findAllForUser(hql, farmId, date1, date2, sickSpecies, status, currentPage, lineSize);
		return list;
	}

	@Override
	public List<EpidemicReport> findAllForAdmin(String adminLoc,String address, String date1,
			String date2, int status, int currentPage, int lineSize) {
		String hql = "from EpidemicReport as epi where epi.farm.location like :adminLoc and epi.farm.location like :address and epi.status = :status "
				+ "and epi.date between :date1 and :date2 order by epi.date desc";
		List<EpidemicReport> list = epiDao.findAllForAdmin(hql, adminLoc, address, date1, date2, status, currentPage, lineSize);
		return list;
	}

	public IEpidemicReportDAO getEpiDao() {
		return epiDao;
	}

	public void setEpiDao(IEpidemicReportDAO epiDao) {
		this.epiDao = epiDao;
	}

	@Override
	public List<EpidemicReport> findAllLoc(String province, String city,String search) {
		String hql = "";
		if(city == null || "".equals(city)){
			 hql = "from EpidemicReport as epi where epi.farm.province = :province or epi.farm.city = :city and epi.farm.location like :search and epi.status > 0 and epi.status <3";
		} else {
			 hql = "from EpidemicReport as epi where epi.farm.province = :province and epi.farm.city = :city and epi.farm.location like :search and epi.status > 0 and epi.status <3";
		}
		List<EpidemicReport> list = epiDao.findAllLoc(hql,province,city,search);
		return list;
	}

	@Override
	public boolean changeStatus(int id) {
		String hql = "update EpidemicReport as epi set epi.status=epi.status+1 where epi.id = :id";
		epiDao.changeStatus(hql, id);
		return true;
	}

	@Override
	public double findCount(String farmId, String date1, String date2, int status) {
		String hql = "from EpidemicReport as epi where epi.farm.farmId = :farmId and  epi.status >= :status "
				+ "and epi.date between :date1 and :date2";
		double aa = epiDao.findCount(hql,farmId,date1,date2,status);
		return aa;
	}

	
}
