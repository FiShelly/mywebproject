package com.lspro.dao.inter;

/**
 * Description:
 * 此接口继承了IDAO接口,以便用于功能的扩展.<br>
 * @author 谢福成
 * @see IDAO
 * @see ProductionRecords
 * @version 1.0
 */

import java.util.List;

import com.lspro.pojo.ProductionRecords;

public interface IProductRecordDAO extends IDAO<ProductionRecords>{
	
	public List<ProductionRecords> findAll(String hql,String farmId,String keyWord, int currentPage, int lineSize,String date1,String date2) ;

	public List<ProductionRecords> finddcr(String hql,String farmId,String date1,String date2);
}
