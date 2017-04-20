package com.teacherwork.service;

import java.io.Serializable;
import java.util.List;

public interface IService<T> {
//	增加数据
	boolean doInsert(T enrity);
//  更新数据
	boolean doUpdate(T entity);
//	删除数据
	boolean doDelete(Class<T> entityClass, Serializable id);
//  查找数据
	T findById(Class<T> entityClass, Serializable id);
//  获取记录数目
	public Integer getAllrecord();
//	查找全部
	List<T> findAll(Class<T> entityClazz);
//	根据年份查找
	List<T> findAllByYear(Class<T> entiClazz,int years);
	
	int doBatchInsert(List<T> list);

	
}
