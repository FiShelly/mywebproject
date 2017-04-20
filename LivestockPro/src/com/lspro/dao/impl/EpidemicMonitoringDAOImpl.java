package com.lspro.dao.impl;

/**
 * Desperation:
 * 此操作类实现了IEpidemicMonitoringDAO接口，用于防疫监测记录基本数据表增删改查方法的实现<br>
 * @author 谢福成
 * @see IEpidemicMonitoringDAO
 * @see EpidemicMonitoring
 * @version 1.0
 */

import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IEpidemicMonitoringDAO;
import com.lspro.pojo.EpidemicMonitoring;

public class EpidemicMonitoringDAOImpl extends DAOImpl<EpidemicMonitoring> implements IEpidemicMonitoringDAO {
	
	public Boolean doDeleteAboutFarm(String farmId)   {	
		String hql = "delete EpidemicMonitoring dis where dis.farm.farmId = :farmId";
		this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).executeUpdate();
		return true;
	}

	@Override
	public List<EpidemicMonitoring> findAll(String hql,String farmId, String keyWord,int currentPage, int lineSize, String date1, String date2){
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).setString("name", "%" + keyWord + "%").setString("date1", date1).setString("date2", date2);  //分页查询
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<EpidemicMonitoring> list = query.list();
		return list;
	}

	@Override
	public Integer findCount(String hql, String farmId, String date1,
			String date2) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).setString("date1", date1).setString("date2", date2);  //分页查询
		List list = query.list();
		Number num = (Number) list.get(0); 
		return num.intValue();
	}

}
