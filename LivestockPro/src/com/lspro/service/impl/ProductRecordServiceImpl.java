package com.lspro.service.impl;

import java.util.List;

import com.lspro.dao.inter.IProductRecordDAO;
import com.lspro.pojo.ProductionRecords;
import com.lspro.service.ProductRecordService;

public class ProductRecordServiceImpl extends ServiceImpl<ProductionRecords> implements ProductRecordService {
 
	private IProductRecordDAO proDao ;
	
	public Boolean doDeleteAboutFarm(String farmId) {
		return this.getProDao().doDeleteAboutFarm(farmId);
	}

	@Override
	public List<ProductionRecords> findAll(String farmId, String keyWord,int currentPage, int lineSize, String date1, String date2){
		String hql = "from ProductionRecords as pro " +
				"where pro.farm.farmId = :farmId and (pro.roomNum like :name or pro.recordDate like :name or pro.birthNum like :name or " +
				"pro.putNum like :name or pro.inNum like :name or pro.deadNum like :name or pro.remainNum like :name or pro.note like :name) " +
				"and pro.recordDate between :date1 and :date2 order by pro.recordDate desc ";
		List<ProductionRecords> list = this.getProDao().findAll(hql, farmId, keyWord, currentPage, lineSize, date1, date2);
		return list;
	}

	public IProductRecordDAO getProDao() {
		return proDao;
	}

	public void setProDao(IProductRecordDAO proDao) {
		this.proDao = proDao;
	}

	@Override
	public double finddcr(String farmId, String date1, String date2) {
		String hql = "from ProductionRecords as pro "
				+ "where pro.farm.farmId = :farmId and pro.recordDate between :date1 and :date2 order by pro.recordDate desc ";
		List<ProductionRecords> list = proDao.finddcr(hql, farmId, date1, date2);
		int remainNum = 0;
		int out = 0;
		for(ProductionRecords pro : list){
			remainNum = remainNum+pro.getRemainNum();
			out = out + pro.getDeadNum() + pro.getInNum();
		}
		if(remainNum == 0){
			return 0;
		} else {
			return ( out*1.0) / (out+remainNum);
		}
		
	}



	
}
