package com.lspro.service;

import java.io.Serializable;
import java.util.List;


public interface ServiceInter<T> {
	public boolean doCreateOrUpdate(T vo)   ;

	public boolean doDelete(Class<T> entityClass,Serializable id)  ;

	public T findById(Class<T> entityClass,Serializable id)  ;
	
	public List<T> findAll(String hql,String keyWord, int currentPage, int lineSize) ;

	public Integer getAllrecord(String keyWord)  ;
	
	public Boolean doDeleteAboutFarm(String farmId)  ;
	
	public boolean doBatchDelete(Class<T> entityClass,Serializable[] ids);

	
}
