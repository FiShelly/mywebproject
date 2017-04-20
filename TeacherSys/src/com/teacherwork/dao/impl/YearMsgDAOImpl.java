package com.teacherwork.dao.impl;

import org.hibernate.Query;

import com.teacherwork.dao.IYearMsgDAO;
import com.teacherwork.domain.YearMsg;

public class YearMsgDAOImpl extends DAOImpl<YearMsg> implements IYearMsgDAO {

	@Override
	public YearMsg findByCurrent(String hql,boolean isCurrent) {
		YearMsg res = (YearMsg) this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setBoolean("isCurrent", isCurrent).uniqueResult();
		return res;
	}

	@Override
	public int doInsertSal(String hql, YearMsg msg) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setDouble("sal", msg.getSal()).setInteger("years", msg.getYears());
		return query.executeUpdate();
	}

	@Override
	public int doUpdateCurYear(String hql, int years,boolean isCurrent) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setInteger("years", years).setBoolean("isCurrent", isCurrent);
		return query.executeUpdate();
	}
	
}
