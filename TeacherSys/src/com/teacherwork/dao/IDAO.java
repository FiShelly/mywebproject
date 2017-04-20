package com.teacherwork.dao;

import java.io.Serializable;
import java.util.List;

public interface IDAO<T> {

//	增加数据
	void doInsert(T enrity);
//  更新数据
	void doUpdate(T entity);
//	删除数据
	void doDelete(Class<T> entityClass, Serializable id);
//  查找数据
	T findById(Class<T> entityClass, Serializable id);
//  获取记录数目
	public Integer getAllrecord();
//  设置数据记录数目
	public void setRecord(Serializable count);
//	查找全部
	List<T> findAll(String hql);
//	根据年份查找
	List<T> findAllByYear(String hql,int years);
	
	void doBatchInsert(List<T> list);
	
	void doBatchUpdate(List<T> list);
}
