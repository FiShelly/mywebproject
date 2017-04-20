package com.teacherwork.service.impl;

import java.io.Serializable;
import java.util.List;

import com.teacherwork.dao.IDAO;
import com.teacherwork.service.IService;

public class ServiceImpl<T> implements IService<T> {

	private IDAO<T> dao;
	
	@Override
	public boolean doInsert(T enrity) {
		this.getDao().doInsert(enrity);
		return true;
	}

	@Override
	public boolean doUpdate(T entity) {
		this.getDao().doUpdate(entity);
		return true;
	}

	@Override
	public boolean doDelete(Class<T> entityClass, Serializable id) {
		this.getDao().doDelete(entityClass, id);
		return true;
	}

	@Override
	public T findById(Class<T> entityClass, Serializable id) {
		return this.getDao().findById(entityClass, id);
	}

	@Override
	public Integer getAllrecord() {
		return this.getDao().getAllrecord();
	}
	
	@Override
	public List<T> findAll(Class<T> entityClazz) {
		String hql = "from " + entityClazz.getSimpleName() + " obj ";
		return this.getDao().findAll(hql);
	}


	public IDAO<T> getDao() {
		return dao;
	}

	public void setDao(IDAO<T> dao) {
		this.dao = dao;
	}

	@Override
	public List<T> findAllByYear(Class<T> entiClazz, int years) {
		String hql = "from " + entiClazz.getSimpleName() + " obj where obj.yearMsg.years = :years";
		return this.getDao().findAllByYear(hql, years);
	}

	@Override
	public int doBatchInsert(List<T> list) {
		this.getDao().doBatchInsert(list);
		return list.size();
	}


}
