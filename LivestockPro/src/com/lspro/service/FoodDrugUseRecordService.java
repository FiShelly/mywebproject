package com.lspro.service;

import java.util.List;
import com.lspro.pojo.FoodDrugUseRecord;

public interface FoodDrugUseRecordService extends ServiceInter<FoodDrugUseRecord> {
	public List<FoodDrugUseRecord> findAll(String farmId,String keyWord, int currentPage, int lineSize,
			String date1,String date2,String date3,String date4);
}
