package com.lspro.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IUsersDAO;
import com.lspro.pojo.Users;

public class UsersDAOImpl extends DAOImpl<Users> implements IUsersDAO {

	public Boolean doDeleteAboutFarm(String farmId)   {
		String hql = "delete Users users where users.farm.farmId = :farmId";
		this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).executeUpdate();
		return true;
	}
	
	@Override
	public List<Users> findLogin(String hql, Users users)   {
		List<Users> list = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("Id", users.getLoginId()).setString("pw", users.getPassword()).list();
		return list;
	}

	@Override
	public Users findByFarmId(String hql, String farmId)   {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId);
		Users user = (Users) query.list().get(0);
		return user;
	}

	@Override
	public List<Users> checkId(String hql, String loginId)   {
		List<Users> list = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("loginId", loginId).list();
		return list;
	}
}
