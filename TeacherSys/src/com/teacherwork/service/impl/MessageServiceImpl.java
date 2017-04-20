package com.teacherwork.service.impl;

import java.util.List;

import com.teacherwork.dao.IMessageDAO;
import com.teacherwork.domain.Message;
import com.teacherwork.service.IMessageService;

public class MessageServiceImpl extends ServiceImpl<Message> implements IMessageService {

	private IMessageDAO msgDao;

	public IMessageDAO getMsgDao() {
		return msgDao;
	}

	public void setMsgDao(IMessageDAO msgDao) {
		this.msgDao = msgDao;
	}

	@Override
	public List<Message> findAllFromByPage(int currentPage, int lineSize,
			String id, boolean flag) {
		String hql = "from Message msg ";
		if(flag){
			hql = hql + "where msg.toId = :toId order by msg.passState ";
		} else {
			hql = hql + "where msg.toId = :toId";
		}
		return this.getMsgDao().findAllFromByPage(hql,currentPage,lineSize,id,flag);
	}

	@Override
	public boolean updateReadState(boolean state, String fromId) {
		String hql = "update Message msg set msg.isRead = :isRead where msg.id = :fromId";
		int count = this.getMsgDao().updateReadState(hql, state,  fromId) ;
		if(count == 1){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updatePassState(int state, String fromId) {
		String hql = "update Message msg set msg.passState = :passState where msg.id = :fromId";
		int count = this.getMsgDao().updateReadState(hql, state,  fromId) ;
		if(count == 1){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean findRepeatSub(String fromId,int years) {
		String hql = "select msg.passState from Message msg where msg.fromId = :fromId and msg.years = :years";
		int result = this.getMsgDao().findRepeatSub(hql,fromId, years);
		if(result == 0){
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int findNotReadOrReadCount(String toId, boolean isRead) {
		String hql = "select count(msg.id) from Message msg where msg.toId = :toId and msg.isRead = :isRead";
		int count = this.getMsgDao().findNotReadOrReadCount(hql, toId,  isRead) ;
		return count;
	}

	@Override
	public int findNotHandlerOrHandlerCount(int passState) {
		String hql = "select count(msg.id) from Message msg where msg.passState = :passState and msg.fromRole = false";
		int count = this.getMsgDao().findNotHandlerOrHandlerCount(hql,  passState) ;
		return count;
	}
}