package com.teacherwork.service;

import java.util.List;

import com.teacherwork.domain.User;

public interface IUserService extends IService<User> {
	boolean findLogin(User user);

	List<User> findAllByPage(int currentPage,int lineSize,int role);
	
	boolean findExist(String loginId);
	
	boolean doUpdatePw(String userId,String npw);
	
	
}
