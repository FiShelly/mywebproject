package com.teacherwork.service;

import com.teacherwork.domain.YearMsg;

public interface IYearMsgService extends IService<YearMsg> {

	YearMsg findByCurrent(boolean isCurrent);
	
	boolean doInsertSal(YearMsg msg);
	
	boolean doUpdateCurYear(int years, boolean isCurrent);
}
