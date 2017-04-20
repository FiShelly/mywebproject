package com.teacherwork.service;

import java.util.List;

import com.teacherwork.domain.PreReview;

public interface IPreReviewService extends IService<PreReview> {

	 List<PreReview> findAllByPage( int currentPage,int lineSize);

	 List<PreReview> findAllByPageAndYear(int years, int currentPage,int lineSize);
	 
}
