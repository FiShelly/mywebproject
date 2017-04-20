package com.lspro.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IEpidemicReportDAO;
import com.lspro.pojo.EpidemicReport;

public class EpidemicReportDAOImpl extends DAOImpl<EpidemicReport> implements IEpidemicReportDAO {

	@Override
	public List<EpidemicReport> findAllForUser(String hql, String farmId,
			String date1, String date2, String sickSpecies, int status,
			int currentPage, int lineSize) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).
				setString("date1", date1).setString("date2", date2).setInteger("status", status).setString("sickSpecies", "%"+sickSpecies+"%");  //分页查询
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<EpidemicReport> list = query.list();
		return list;
	}

	@Override
	public List<EpidemicReport> findAllForAdmin(String hql, String adminLoc,String address,
			String date1, String date2, int status, int currentPage,
			int lineSize) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("adminLoc", "%"+adminLoc+"%").
				setString("date1", date1).setString("date2", date2).setInteger("status", status).setString("address", "%"+address+"%");  //分页查询
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<EpidemicReport> list = query.list();
		return list;
	}

	@Override
	public List<EpidemicReport> findAllLoc(String hql, String province,String city,String search) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("province", province).setString("search", "%"+search+"%").
				setString("city", city);  //分页查询
		List<EpidemicReport> list = query.list();
		return list;
	}

	@Override
	public void changeStatus(String hql,int id) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setInteger("id", id);
		query.executeUpdate();
	}

	@Override
	public double findCount(String hql, String farmId, String date1,
			String date2, int status) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).
				setString("date1", date1).setString("date2", date2).setInteger("status", status);  //分页查询
		this.setRecord(query.list().size());
		List<EpidemicReport> list = query.list();
		return list.size();
	}
	
}
