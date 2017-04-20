package com.lspro.dao.inter;


/**
 * Description:
 * 此接口继承了IDAO接口,以便用于功能的扩展.<br>
 * @author 谢福成
 * @see IDAO
 * @see FoodDrugUseRecord
 * @version 1.0
 */

import java.util.List;


import com.lspro.pojo.FoodDrugUseRecord;


public interface IFoodDrugUseRecordDAO extends IDAO<FoodDrugUseRecord> {
	public List<FoodDrugUseRecord> findAll(String hql,String farmId,String keyWord, int currentPage, int lineSize,
			String date1,String date2,String date3,String date4) ;
}
