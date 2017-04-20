package com.teacherwork.service.impl;

import java.util.List;

import com.teacherwork.dao.IUserDAO;
import com.teacherwork.domain.User;
import com.teacherwork.service.IUserService;

public class UserServiceImpl extends ServiceImpl<User> implements IUserService {

	private IUserDAO uDao;
	
	@Override
	public boolean findLogin(User user) {
		String hql = "from User user where user.loginId = :loginId and user.pw = :pw and user.role = :role";
		User re = this.getuDao().findLogin(hql, user);
		if(re != null){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<User> findAllByPage(int currentPage, int lineSize,int role) {
		String hql = "from User user where user.role = :role";
		return this.uDao.findAllByPage(hql, currentPage, lineSize,role);
	}

	@Override
	public boolean findExist(String loginId) {
		String hql = "from User user where user.loginId = :loginId ";
		User re = this.getuDao().findExist(hql, loginId);
		if(re != null){
			return true;
		} else {
			return false;
		}
	}

	public IUserDAO getuDao() {
		return uDao;
	}

	public void setuDao(IUserDAO uDao) {
		this.uDao = uDao;
	}

	@Override
	public boolean doUpdatePw(String userId, String npw) {
		String hql = "update User as u set u.pw = :pw where u.loginId = :loginId";
		if(uDao.doUpdatePw(hql, userId, npw) == 1){
			return true;
		} else {
			return false;
		}
	}

}
