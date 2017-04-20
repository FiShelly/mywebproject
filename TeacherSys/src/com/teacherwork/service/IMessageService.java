package com.teacherwork.service;

import java.util.List;

import com.teacherwork.domain.Message;

public interface IMessageService extends IService<Message> {
//	flag true 查找发送方， false为查找接收方
	List<Message> findAllFromByPage(int currentPage, int lineSize,String id,boolean flag);

	boolean updateReadState(boolean state,String fromId);
	
	boolean updatePassState(int state,String fromId);
	
	boolean findRepeatSub(String fromId,int years);
	
	int findNotReadOrReadCount(String toId,boolean isRead);
	
	int findNotHandlerOrHandlerCount(int state);
}
