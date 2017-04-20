package com.lspro.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IImmuneProgramDAO;
import com.lspro.pojo.ImmunePro;

public class ImmuneProgramDAOImpl extends DAOImpl<ImmunePro> implements IImmuneProgramDAO {

	public Boolean doDeleteAboutFarm(String farmId)   {
		String hql = "delete ImmunePro dis where dis.farm.farmId = :farmId";
		this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).executeUpdate();
		return true;
	}

	@Override
	public List<ImmunePro> checkName(String hql,String name, String farmId)   {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("name", name).setString("farmId", farmId);
		return query.list();
	}

	@Override
	public List<ImmunePro> findImmunePro(String hql,String name, String farmId)   {
		List<ImmunePro> list = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("name", name).setString("farmId", farmId).list();
		return list;
	}

	@Override
	public List<String> findSpecies(String hql,String farmId)   {
		List<String> list = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).list();
		return list;
	}
	
}
