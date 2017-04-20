package com.lspro.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.lspro.dao.inter.IDAO;

public class DAOImpl<T> implements IDAO<T> {
	
	private int pageCount;
	
	private SessionFactory sessionFactory;
	@Override
	public void doCreateOrUpdate(T vo)   {
		this.getSessionFactory().getCurrentSession().merge(vo);		
	}

	@Override
	public void doDelete(Class<T> entityClass, Serializable id)
			  {
		T temp = this.findById(entityClass, id);
		this.getSessionFactory().getCurrentSession().delete(temp);
	}

	@Override
	public T findById(Class<T> entityClass, Serializable id)   {
		T result = (T) this.getSessionFactory().getCurrentSession().get(entityClass, id);
		return result;
	}

 
	protected Query findAll(String hql)   {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		pageCount = query.list().size();
		return query;
	}
	
	@Override
	public List<T> findAll(String hql, String keyWord, int currentPage,int lineSize)   {
		List<T> list = this.findAll(hql).setFirstResult((currentPage - 1 ) * lineSize).setMaxResults(lineSize).list();
		pageCount = list.size();
		return list;
	}

	@Override
	public Integer getAllrecord()   {
		return pageCount;
	}

	@Override
	public Boolean doDeleteAboutFarm(String farmId)   {
		return false;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void setRecord(Serializable count)   {
		 this.pageCount = (Integer) count;
	}

	@Override
	public void doBatchDelete(Class<T> entityClass, Serializable[] ids) {
		for(Serializable id : ids){
			doDelete(entityClass, id);
		}
	}
}
