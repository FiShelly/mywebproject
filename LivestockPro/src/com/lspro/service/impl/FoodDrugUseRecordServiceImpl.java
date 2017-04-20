package com.lspro.service.impl;

import java.util.List;

import com.lspro.dao.inter.IFoodDrugUseRecordDAO;
import com.lspro.pojo.FoodDrugUseRecord;
import com.lspro.service.FoodDrugUseRecordService;

public class FoodDrugUseRecordServiceImpl extends ServiceImpl<FoodDrugUseRecord> implements FoodDrugUseRecordService {
	
	private IFoodDrugUseRecordDAO foodDao ;
	
	public Boolean doDeleteAboutFarm(String farmId)  {
		return foodDao.doDeleteAboutFarm(farmId);
	}

	@Override
	public List<FoodDrugUseRecord> findAll(String farmId, String keyWord,int currentPage, int lineSize, String date1, String date2,
			String date3, String date4) {
			String hql = "from FoodDrugUseRecord as fdur " +
					"where fdur.farm.farmId = :farmId and " +
					"(fdur.startTime like :name or fdur.productName like :name or fdur.manufacturer like :name or fdur.batchNum like :name or " +
					"fdur.dosage like :name or fdur.stopTime like :name or fdur.processDate like:name or fdur.note like :name) and " +
					"(fdur.startTime between :date1 and :date2) and (fdur.stopTime between :date3 and :date4) order by fdur.startTime desc";
			List<FoodDrugUseRecord> list = this.getFoodDao().findAll(hql, farmId, keyWord, currentPage, lineSize, date1, date2, date3, date4);
			return list;
	}

	public IFoodDrugUseRecordDAO getFoodDao() {
		return foodDao;
	}

	public void setFoodDao(IFoodDrugUseRecordDAO foodDao) {
		this.foodDao = foodDao;
	}
}
