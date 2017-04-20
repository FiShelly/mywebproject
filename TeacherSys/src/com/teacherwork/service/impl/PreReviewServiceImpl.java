package com.teacherwork.service.impl;

import java.util.List;

import com.teacherwork.dao.IPreReviewDAO;
import com.teacherwork.domain.PreReview;
import com.teacherwork.service.IPreReviewService;

public class PreReviewServiceImpl extends ServiceImpl<PreReview> implements IPreReviewService {

	private IPreReviewDAO prDao;
	
	@Override
	public List<PreReview> findAllByPage(int currentPage, int lineSize) {
		String hql = "from PreReview pr";
		return this.getPrDao().findAllByPage(hql, currentPage, lineSize);
	}

	public IPreReviewDAO getPrDao() {
		return prDao;
	}

	public void setPrDao(IPreReviewDAO prDao) {
		this.prDao = prDao;
	}

	@Override
	public List<PreReview> findAllByPageAndYear(int years, int currentPage,int lineSize) {
		String hql = "from PreReview pr where pr.years = :years";
		return this.getPrDao().findAllByPageAndYear(hql,years, currentPage, lineSize);
	}

}
