package com.teacherwork.dao;

import com.teacherwork.domain.YearMsg;

public interface IYearMsgDAO extends IDAO<YearMsg>{

	YearMsg findByCurrent(String hql,boolean isCurrent);

	int doInsertSal(String hql, YearMsg msg);

	int doUpdateCurYear(String hql, int years, boolean isCurrent);
}
