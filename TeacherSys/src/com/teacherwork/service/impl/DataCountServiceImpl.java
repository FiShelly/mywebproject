package com.teacherwork.service.impl;

import java.util.List;

import com.teacherwork.dao.IDataCountDAO;
import com.teacherwork.domain.DataCount;
import com.teacherwork.service.IDataCountService;

public class DataCountServiceImpl extends ServiceImpl<DataCount> implements IDataCountService {

	private IDataCountDAO dcDao;
	
	@Override
	public List<DataCount> findAllByPage(int currentPage, int lineSize,boolean isUp) {
		String hql = "from DataCount dc where dc.term.isLastTerm = :isup";
		return this.getDcDao().findAllByPage(hql, currentPage, lineSize, isUp);
	}
	
	public IDataCountDAO getDcDao() {
		return dcDao;
	}

	public void setDcDao(IDataCountDAO dcDao) {
		this.dcDao = dcDao;
	}

	@Override
	public DataCount findViewById(String loginId, int years,boolean state) {
		String hql = "from DataCount dc where dc.loginId = :loginId and dc.years = :years and dc.state = :state";
		return this.getDcDao().findViewById(hql, loginId, years,state);
	}

	@Override
	public List<DataCount> findAllByPage(int currentPage, int lineSize,boolean state, int term,int years) {
		String hql = "from DataCount dc where dc.state = :state  and dc.years = :years ";
		if(term != 3 ){
			hql = hql + "and dc.term = :term ";
		} else {
			hql = hql + "and dc.term < :term ";
		}
		hql = hql + " order by dc.user.userName ,dc.term ";
		return this.getDcDao().findAllByPage(hql, currentPage, lineSize, state,term,years);
	}

	@Override
	public List<DataCount> findAllByYearAndTerm(boolean state, int term,int years) {
		String hql = "from DataCount dc where dc.state = :state  and dc.years = :years ";
		if(term != 3 ){
			hql = hql + "and dc.term = :term ";
		} else {
			hql = hql + "and dc.term < :term ";
		}
		hql = hql + " order by dc.user.userName ,dc.term ";
		return this.getDcDao().findAllByYearAndTerm(hql, state,term,years);
	}

	@Override
	public DataCount findViewById(String loginId, int years, boolean state,
			int term) {
		String hql = "from DataCount dc where dc.loginId = :loginId and dc.years = :years and dc.state = :state and dc.term = :term";
		return this.getDcDao().findViewById(hql, loginId, years,state,term);
	}

}
