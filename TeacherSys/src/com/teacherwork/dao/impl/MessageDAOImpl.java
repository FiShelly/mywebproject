package com.teacherwork.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.teacherwork.dao.IMessageDAO;
import com.teacherwork.domain.Message;

@SuppressWarnings("unchecked")
public class MessageDAOImpl extends DAOImpl<Message> implements IMessageDAO {

	@Override
	public List<Message> findAllFromByPage(String hql, int currentPage,int lineSize, String id, boolean flag) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString("toId", id);
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<Message> list = query.list();
		return list;
	}

	@Override
	public int updateReadState(String hql, boolean state, String fromId) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setBoolean("isRead", state).setString("fromId", fromId);
		return query.executeUpdate();
	}

	@Override
	public int updateReadState(String hql, int state, String fromId) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setInteger("passState", state).setString("fromId", fromId);
		return query.executeUpdate();
	}

	@Override
	public int findRepeatSub(String hql, String fromId, int years) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setString("fromId", fromId).setInteger("years", years);
		if(query.uniqueResult()==null){
			return -1;
		} else{
			return (Integer)query.uniqueResult();
		}
	}

	@Override
	public int findNotHandlerOrHandlerCount(String hql, int passState) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setInteger("passState", passState);
		return ((Long)query.uniqueResult()).intValue();
	}

	@Override
	public int findNotReadOrReadCount(String hql, String toId, boolean isRead) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setBoolean("isRead", isRead).setString("toId", toId);
		return ((Long)query.uniqueResult()).intValue();
	}
}
