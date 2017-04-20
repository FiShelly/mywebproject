package com.lspro.service.impl;

import java.io.Serializable;
import java.util.List;

import com.lspro.dao.inter.IDAO;
import com.lspro.service.ServiceInter;

public class ServiceImpl<T> implements ServiceInter<T> {

	private IDAO<T> dao;

	public IDAO<T> getDao() {
		return dao;
	}

	public void setDao(IDAO<T> dao) {
		this.dao = dao;
	}

	@Override
	public boolean doCreateOrUpdate(T vo) {
		dao.doCreateOrUpdate(vo);
		return true;
		
	}

	@Override
	public boolean doDelete(Class<T> entityClass, Serializable id) {
		dao.doDelete(entityClass, id);;
		return true;
	}

	@Override
	public T findById(Class<T> entityClass, Serializable id) {
		T entity = dao.findById(entityClass, id);
		return entity;
	}

	@Override
	public List<T> findAll(String hql, String keyWord, int currentPage,int lineSize) {
		List<T> list = dao.findAll(hql, keyWord, currentPage, lineSize);
		return list;
	}

	@Override
	public Integer getAllrecord(String keyWord) {
		return dao.getAllrecord();
	}

	@Override
	public Boolean doDeleteAboutFarm(String farmId) {
		return false;
	}

	@Override
	public boolean doBatchDelete(Class<T> entityClass, Serializable[] ids) {
		dao.doBatchDelete(entityClass, ids);
		return true;
	}

}
