package com.lspro.service.impl;



import java.util.List;

import com.lspro.dao.inter.IEpidemicMonitoringDAO;
import com.lspro.pojo.EpidemicMonitoring;
import com.lspro.service.EpidemicMonitoringService;

public class EpidemicMonitoringServiceImpl extends ServiceImpl<EpidemicMonitoring> implements EpidemicMonitoringService {
	
	private IEpidemicMonitoringDAO ieDao ;
	
	public Boolean doDeleteAboutFarm(String farmId)  {	
		return ieDao.doDeleteAboutFarm(farmId);
	}

	@Override
	public List<EpidemicMonitoring> findAll(String farmId, String keyWord,int currentPage, int lineSize, String date1, String date2){
		String hql ="from EpidemicMonitoring as epi " +
				"where epi.farm.farmId = :farmId and (epi.samplingTime like :name or epi.roomNum like :name or " +
				"epi.samplingNum like :name or epi.monitoringName like :name or epi.monitoringStation like :name or " +
				"epi.monitoringResult like :name or epi.disposalConditions like :name or epi.note like :name) and " +
				"epi.samplingTime between :date1 and :date2 order by epi.samplingTime desc";
		return ieDao.findAll(hql, farmId, keyWord, currentPage, lineSize, date1, date2);
	}

	public IEpidemicMonitoringDAO getIeDao() {
		return ieDao;
	}

	public void setIeDao(IEpidemicMonitoringDAO ieDao) {
		this.ieDao = ieDao;
	}

	@Override
	public double findCount(String farmId, String date1, String date2,int days) {
		String hql ="select count( distinct epi.samplingTime ) from EpidemicMonitoring as epi " +
				"where epi.farm.farmId = :farmId and epi.samplingTime between :date1 and :date2 ";
		int count = ieDao.findCount(hql, farmId, date1, date2);
		return (count*1.0)/days;
	}

}
