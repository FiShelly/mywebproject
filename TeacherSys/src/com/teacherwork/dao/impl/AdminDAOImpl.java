package com.teacherwork.dao.impl;

import com.teacherwork.dao.IAdminDAO;
import com.teacherwork.domain.Admin;

public class AdminDAOImpl extends DAOImpl<Admin> implements IAdminDAO {

	@Override
	public Admin findLogin(String hql, Admin admin) {
		Admin res = (Admin) this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("pw", admin.getPw())
				.setString("loginId", admin.getLoginId()).uniqueResult();
		return res;
	}

}
