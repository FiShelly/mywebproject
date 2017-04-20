package com.teacherwork.dao;

import java.util.List;

import com.teacherwork.domain.PreReview;

public interface IPreReviewDAO extends IDAO<PreReview> {

	List<PreReview> findAllByPage(String hql,int currentPage,int lineSize);

	List<PreReview> findAllByPageAndYear(String hql, int years,int currentPage, int lineSize);
	
}
