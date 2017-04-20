package com.teacherwork.service.impl;

import com.teacherwork.dao.IYearMsgDAO;
import com.teacherwork.domain.YearMsg;
import com.teacherwork.service.IYearMsgService;

public class YearMsgServiceImpl extends ServiceImpl<YearMsg> implements IYearMsgService {

	private IYearMsgDAO msgDao;
	
	@Override
	public YearMsg findByCurrent(boolean isCurrent) {
		String hql = "from YearMsg msg where msg.isCurrent = :isCurrent";
		return msgDao.findByCurrent(hql, isCurrent);
	}

	public IYearMsgDAO getMsgDao() {
		return msgDao;
	}

	public void setMsgDao(IYearMsgDAO msgDao) {
		this.msgDao = msgDao;
	}

	@Override
	public boolean doInsertSal(YearMsg msg) {
		String hql = "update YearMsg as msg set msg.sal = :sal where msg.years = :years";
		int count = msgDao.doInsertSal(hql, msg);
		if(count == 1){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean doUpdateCurYear(int years,boolean isCurrent) {
		String hql = "update YearMsg as msg set msg.isCurrent = :isCurrent where msg.years = :years";
		int count = msgDao.doUpdateCurYear(hql,years,isCurrent);
		if(count == 1){
			return true;
		} else {
			return false;
		}
	}

}
