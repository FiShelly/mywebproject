package com.teacherwork.service.impl;

import com.teacherwork.dao.IAdminDAO;
import com.teacherwork.domain.Admin;
import com.teacherwork.service.IAdminService;

public class AdminServiceImpl extends ServiceImpl<Admin> implements IAdminService {

	private IAdminDAO adminDao;
	
	@Override
	public boolean findLogin(Admin admin) {
		String hql = "from Admin admin where admin.loginId = :loginId and admin.pw = :pw ";
		Admin re = this.getAdminDao().findLogin(hql, admin);
		if(re != null){
			return true;
		} else {
			return false;
		}
	}

	public IAdminDAO getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(IAdminDAO adminDao) {
		this.adminDao = adminDao;
	}

}
