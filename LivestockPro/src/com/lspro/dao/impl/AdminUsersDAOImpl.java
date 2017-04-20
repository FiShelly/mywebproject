package com.lspro.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IAdminUsersDAO;
import com.lspro.pojo.AdminUsers;

public class AdminUsersDAOImpl extends DAOImpl<AdminUsers> implements IAdminUsersDAO {

	public List<AdminUsers> findLogin(String hql,String id,String pw,AdminUsers vo) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString(id, vo.getLoginId())
				.setString(pw, vo.getPassword());
		List<AdminUsers> list = query.list();
		return list;
	}

	@Override
	public List<AdminUsers> findAll(String hql, String sAddress, String loginId,
			String aAddress, String date1, String date2,int currentPage, int lineSize) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("sAddress", sAddress+"%").
				setString("loginId", "%"+loginId+"%").setString("aAddress",  "%"+aAddress+"%").setString("date1", date1).setString("date2", date2);
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1 ) * lineSize);
		query.setMaxResults(lineSize);
		List<AdminUsers> list = query.list();
		return list;
	}

	@Override
	public int updatePw(String hql,String loginId, String pw) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("loginId", loginId).setString("pw", pw);
		return query.executeUpdate();
	}

	@Override
	public List<AdminUsers> checkId(String hql, String loginId) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("loginId", loginId);
		List<AdminUsers> list = query.list();
		return list;
	}
}
