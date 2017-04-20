package com.lspro.dao.impl;

/**
 * Desperation:
 * 此操作类实现了IFoodDrugUseRecordDAO接口，用于饲料、饲料添加剂和兽药使用记录表增删改查方法的实现<br>
 * @author 谢福成
 * @see IFoodDrugUseRecordDAO
 * @see FoodDrugUseRecord
 * @version 2.0(去掉方法中的try...catch块)
 */

import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IFoodDrugUseRecordDAO;
import com.lspro.pojo.FoodDrugUseRecord;

public class FoodDrugUseRecordDAOImpl extends DAOImpl<FoodDrugUseRecord> implements IFoodDrugUseRecordDAO {
	
	public Boolean doDeleteAboutFarm(String farmId)   {
		String hql = "delete FoodDrugUseRecord dis where dis.farm.farmId = :farmId";
		this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).executeUpdate();;
		return true;
	}

	@Override
	public List<FoodDrugUseRecord> findAll(String hql,String farmId, String keyWord,
			int currentPage, int lineSize, String date1, String date2,String date3, String date4)   {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).setString("name", "%" + keyWord + "%").
				setString("date1", date1).setString("date2", date2).setString("date3", date3).setString("date4", date4);  //分页查询
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<FoodDrugUseRecord> list = query.list();
		return list;
	}
}