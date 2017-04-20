package com.teacherwork.dao;

import java.util.List;

import com.teacherwork.domain.Message;

public interface IMessageDAO extends IDAO<Message> {

	List<Message> findAllFromByPage(String hql, int currentPage, int lineSize,String id, boolean flag);

	int updateReadState(String hql, boolean state, String fromId);

	int updateReadState(String hql, int state, String fromId);

	int findRepeatSub(String hql, String fromId, int years);

	int findNotReadOrReadCount(String hql, String toId, boolean isRead);

	int findNotHandlerOrHandlerCount(String hql, int passState);

}
