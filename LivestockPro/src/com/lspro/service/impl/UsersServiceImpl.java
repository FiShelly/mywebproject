package com.lspro.service.impl;

import java.util.List;

import com.lspro.dao.inter.IUsersDAO;
import com.lspro.pojo.Users;
import com.lspro.service.UsersService;

public class UsersServiceImpl extends ServiceImpl<Users> implements UsersService {

	private IUsersDAO userDao;
	
	public Boolean doDeleteAboutFarm(String farmId) {
		return this.getUserDao().doDeleteAboutFarm(farmId);
	}
	
	@Override
	public boolean findLogin(Users users){
		String hql = "from Users us where us.loginId = :Id and us.password = :pw";
		List<Users> list = userDao.findLogin(hql, users);
		if(list!=null && list.size()==1){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Users findByFarmId(String farmId)  {
		String hql = "from Users user where user.farm.farmId = :farmId";
		return userDao.findByFarmId(hql, farmId);
	}

	@Override
	public boolean checkId(String loginId) {
		String hql = "from Users user where user.loginId = :loginId";
		List<Users> list = userDao.checkId(hql, loginId);
		if(list!=null && list.size()==1){
			return true;
		} else {
			return false;
		}
	}

	public IUsersDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(IUsersDAO userDao) {
		this.userDao = userDao;
	}
	 
}
