package com.teacherwork.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.teacherwork.dao.IPreReviewDAO;
import com.teacherwork.domain.PreReview;

public class PreReviewDAOImpl extends DAOImpl<PreReview> implements IPreReviewDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<PreReview> findAllByPage(String hql, int currentPage,
			int lineSize) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql);
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<PreReview> list = query.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<PreReview> findAllByPageAndYear(String hql, int years,
			int currentPage, int lineSize) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setInteger("years",years);
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<PreReview> list = query.list();
		return list;
	}

}
