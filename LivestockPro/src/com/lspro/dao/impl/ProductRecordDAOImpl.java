package com.lspro.dao.impl;

/**
 * Desperation:
 * 此操作类实现了IProductRecordDAO接口，用于生产记录数据表增删改查方法的实现<br>
 * @author 谢福成
 * @see IProductRecordDAO
 * @see ProductionRecords
 * @version 2.0(去掉方法中的try...catch块)
 */

import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IProductRecordDAO;
import com.lspro.pojo.ProductionRecords;

public class ProductRecordDAOImpl extends DAOImpl<ProductionRecords> implements IProductRecordDAO {
 
	public Boolean doDeleteAboutFarm(String farmId)   {
		String hql = "delete ProductionRecords dis where dis.farm.farmId = :farmId";
		this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).executeUpdate();
		return true;
	}

	@Override
	public List<ProductionRecords> findAll(String hql,String farmId, String keyWord,int currentPage, int lineSize, String date1, String date2) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).setString("name", "%" + keyWord + "%").setString("date1", date1).setString("date2", date2);  //分页查询
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<ProductionRecords> list = query.list();
		return list;
	}

	@Override
	public List<ProductionRecords> finddcr(String hql,String farmId,String date1,String date2) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).setString("date1", date1).setString("date2", date2);  //分页查询
		List<ProductionRecords> list = query.list();
		return list;
	}
}
