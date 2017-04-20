package com.teacherwork.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.teacherwork.dao.IDataCountDAO;
import com.teacherwork.domain.DataCount;

public class DataCountDAOImpl extends DAOImpl<DataCount> implements IDataCountDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<DataCount> findAllByPage(String hql, int currentPage, int lineSize,
			boolean isUp) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setBoolean("isup", isUp);
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<DataCount> list = query.list();
		return list;
	}

	@Override
	public DataCount findViewById(String hql, String loginId, int years,boolean state) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("loginId", loginId).
				setInteger("years", years).setBoolean("state",state);
		return (DataCount) query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DataCount> findAllByPage(String hql, int currentPage,
			int lineSize, boolean state, int term, int years) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setBoolean("state", state).
				setInteger("term", term).setInteger("years", years);
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<DataCount> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataCount> findAllByYearAndTerm(String hql, boolean state,
			int term, int years) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setBoolean("state", state).
				setInteger("term", term).setInteger("years", years);
		List<DataCount> list = query.list();
		return list;
	}

	@Override
	public DataCount findViewById(String hql, String loginId, int years,
			boolean state, int term) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("loginId", loginId).
				setInteger("years", years).setBoolean("state",state).setInteger("term", term);
		return (DataCount) query.uniqueResult();
	}
	
	

}
