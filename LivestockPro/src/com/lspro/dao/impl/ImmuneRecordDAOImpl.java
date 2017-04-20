package com.lspro.dao.impl;

/**
 * Desperation:
 * 此操作类实现了IImmuneRecordDAO接口，用于免疫记录数据表增删改查方法的实现<br>
 * @author 谢福成
 * @see IImmuneRecordDAO
 * @see ImmuneRecord
 * @version 1.0
 */

import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IImmuneRecordDAO;
import com.lspro.pojo.ImmuneRecord;

public class ImmuneRecordDAOImpl extends DAOImpl<ImmuneRecord> implements IImmuneRecordDAO {
	
	public Boolean doDeleteAboutFarm(String farmId)   {
		String hql = "delete ImmuneRecord dis where dis.farm.farmId = :farmId";
		this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).executeUpdate();
		return true;
	}

	@Override
	public List<ImmuneRecord> findAll(String hql,String farmId, String keyWord,int currentPage, int lineSize, String date1, String date2){
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).setString("name", "%" + keyWord + "%").setString("date1", date1).setString("date2", date2);  //分页查询
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<ImmuneRecord> list = query.list();
		return list;
	}

	@Override
	public List<ImmuneRecord> findImmRate(String hql, String farmId,String date1, String date2) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).setString("date1", date1).setString("date2", date2);  //分页查询
		List<ImmuneRecord> list = query.list();
		return list;
	}
}
