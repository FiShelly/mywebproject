package com.teacherwork.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.teacherwork.dao.IUserDAO;
import com.teacherwork.domain.User;

public class UserDAOImpl extends DAOImpl<User> implements IUserDAO {

	@Override
	public User findLogin(String hql, User user) {
		User res = (User) this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("pw", user.getPw())
				.setString("loginId", user.getLoginId().trim())
				.setInteger("role", user.getRole()).uniqueResult();
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllByPage(String hql, int currentPage, int lineSize,int role) {
		Query query =  this.getSessionFactory().getCurrentSession().createQuery(hql).setInteger("role",role);
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<User> list = query.list();
		return list;
	}

	@Override
	public User findExist(String hql, String loginId) {
		User res = (User) this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("loginId", loginId).uniqueResult();
		return res;
	}
	
	public int doUpdatePw(String hql,String userId, String npw){
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("loginId", userId).setString("pw", npw);
		return query.executeUpdate();
	}

}
