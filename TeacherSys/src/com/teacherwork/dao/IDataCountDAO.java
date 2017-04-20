package com.teacherwork.dao;

import java.util.List;

import com.teacherwork.domain.DataCount;

public interface IDataCountDAO extends IDAO<DataCount> {

	List<DataCount> findAllByPage(String hql,int currentPage,int lineSize,boolean isUp);
	
	DataCount findViewById(String hql,String loginId,int years,boolean state);

	DataCount findViewById(String hql,String loginId,int years,boolean state,int term);
	
	List<DataCount> findAllByPage(String hql, int currentPage, int lineSize,boolean state, int term, int years);

	List<DataCount> findAllByYearAndTerm(String hql, boolean state, int term,int years);
}
