package com.teacherwork.service;

import java.util.List;

import com.teacherwork.domain.DataCount;

public interface IDataCountService extends IService<DataCount> {
	
	List<DataCount> findAllByPage( int currentPage, int lineSize,boolean isUp);
	
	List<DataCount> findAllByPage( int currentPage, int lineSize,boolean state,int term,int years);
	
	List<DataCount> findAllByYearAndTerm( boolean state,int term,int years);
	
	
	DataCount findViewById(String loginId,int years,boolean state);
	
	DataCount findViewById(String loginId,int years,boolean state,int term);
}
