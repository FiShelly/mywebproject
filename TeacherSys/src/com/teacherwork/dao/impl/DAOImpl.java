package com.teacherwork.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.teacherwork.dao.IDAO;

public class DAOImpl<T> implements IDAO<T> {

	private int pageCount;
	private SessionFactory sessionFactory;
	
	@Override
	public void doInsert(T enrity) {
		sessionFactory.getCurrentSession().save(enrity);
	}

	@Override
	public void doUpdate(T entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void doDelete(Class<T> entityClass, Serializable id) {
		T temp = this.findById(entityClass, id);
		this.getSessionFactory().getCurrentSession().delete(temp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Class<T> entityClass, Serializable id) {
		
		T result = (T) this.getSessionFactory().getCurrentSession().get(entityClass, id);
		return result;
	}

	@Override
	public Integer getAllrecord() {
		return pageCount;
	}

	@Override
	public void setRecord(Serializable count) {
		this.pageCount = (Integer) count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String hql) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		List<T> list =  query.list();
		pageCount = list.size();
		return list;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByYear(String hql,int years) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setInteger("years", years);
		List<T> list =  query.list();
		pageCount = list.size();
		return list;
	}

	@Override
	public void doBatchInsert(List<T> list) {
		Iterator<T> iterator = list.iterator();
		while(iterator.hasNext()){
			T t = iterator.next();
			this.getSessionFactory().getCurrentSession().save(t);
			iterator.remove();
		}
	}

	@Override
	public void doBatchUpdate(List<T> list) {
		// TODO Auto-generated method stub
		
	}


}
