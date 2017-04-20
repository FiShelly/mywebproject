package com.teacherwork.dao;

import java.util.List;

import com.teacherwork.domain.User;

public interface IUserDAO extends IDAO<User> {

	User findLogin(String hql,User user);

	List<User> findAllByPage(String hql,int currentPage,int lineSize,int role);
	
	User findExist(String hql,String loginId);
	
	int doUpdatePw(String hql,String userId, String npw);
}
