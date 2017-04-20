package com.lspro.dao.impl;

/**
 * Desperation:
 * 此操作类实现了IMedicalRecordDAO接口，用于诊疗记录基本数据表改查方法的实现<br>
 * @author 谢福成
 * @see IMedicalRecordDAO
 * @see MedicalRecord
 * @version 1.0
 */


import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IMedicalRecordDAO;
import com.lspro.pojo.MedicalRecord;

public class MedicalRecordDAOImpl extends DAOImpl<MedicalRecord> implements IMedicalRecordDAO {

	public Boolean doDeleteAboutFarm(String farmId)   {
		// TODO Auto-generated method stub
		String hql = "delete MedicalRecord dis where dis.farm.farmId = :farmId";
		this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).executeUpdate();
		return true;
	}

	@Override
	public List<MedicalRecord> findAll(String hql,String farmId, String keyWord,int currentPage, int lineSize, String date1, String date2)  {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).setString("name", "%" + keyWord + "%").setString("date1", date1).setString("date2", date2);  //分页查询
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<MedicalRecord> list = query.list();
		return list;
	}
	
	public List<MedicalRecord> findMedRate(String hql,String farmId,String date1,String date2){
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).setString("date1", date1).setString("date2", date2);  //分页查询
		List<MedicalRecord> list = query.list();
		return list;
	}
}
