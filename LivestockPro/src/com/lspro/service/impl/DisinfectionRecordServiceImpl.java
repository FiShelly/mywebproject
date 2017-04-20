package com.lspro.service.impl;


import java.util.List;

import com.lspro.dao.inter.IDisinfectionRecordDAO;
import com.lspro.pojo.DisinfectionRecord;
import com.lspro.service.DisinfectionRecordService;

public class DisinfectionRecordServiceImpl extends ServiceImpl<DisinfectionRecord> implements DisinfectionRecordService {

	private IDisinfectionRecordDAO disinDao ;
	
	public List<DisinfectionRecord> findAll(String farmId, String keyWord,
		int currentPage, int lineSize, String date1, String date2)  {
		String hql = "from DisinfectionRecord as dis where dis.farm.farmId = :farmId and "
	 		+ "(dis.disinfectionTime like :name or dis.place like :name or dis.disinfectionName like :name or "
	 		+ "dis.disinfectionDose like :name or dis.method like :name or dis.sign like :name) and "
	 		+ "dis.disinfectionTime between :date1 and :date2 order by dis.disinfectionTime desc ";
		 List<DisinfectionRecord> list = this.getDisinDao().findAll(hql,farmId, keyWord, currentPage, lineSize, date1, date2);
		 return list;
	}
	 
	public Boolean doDeleteAboutFarm(String farmId) {
		return getDisinDao().doDeleteAboutFarm(farmId);
	}

	public IDisinfectionRecordDAO getDisinDao() {
		return disinDao;
	}

	public void setDisinDao(IDisinfectionRecordDAO disinDao) {
		this.disinDao = disinDao;
	}

	@Override
	public double findCount(String farmId,String date1, String date2, int days) {
		String hql = "select count(distinct dis.disinfectionTime)  from DisinfectionRecord as dis "
				+ "where dis.farm.farmId = :farmId and dis.disinfectionTime between :date1 and :date2";
		return disinDao.findCount(hql, farmId, date1, date2)/(days*1.0);
	}
}
