package com.lspro.dao.impl;

/**
 * Desperation:
 * 此操作类实现了IAnimalADAO接口，用于动物检疫合格证明表(动物A)增删改查方法的实现<br>
 * @author 谢福成
 * @see IAnimalADAO
 * @see AnimalA
 * @version 1.0
 */
import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IAnimalADAO;
import com.lspro.pojo.AnimalA;

public class AnimalADAOImpl extends DAOImpl<AnimalA> implements IAnimalADAO {

	@Override
	public List<AnimalA> findAll(String hql,String role, String loc, String id,
			String shipperName, String date1,String date2, String animalSpecies,
			int currentPage, int lineSize) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("id", "%" + id + "%")
				.setString("startAddress", (role.equals("user")?loc: "%" + loc + "%"))
				.setString("shipperName", "%" + shipperName + "%")
				.setString("date1", date1).setString("date2", date2)
				.setString("animalSpecies", "%" + animalSpecies + "%");
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1) * lineSize);
		query.setMaxResults(lineSize);
		List<AnimalA> list = query.list();
		return list;
	}

	@Override
	public double findCount(String hql, String address,String date1, String date2) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql)
				.setString("startAddress", address).setString("date1", date1).setString("date2", date2);
		List list = query.list();
		Number num = (Number) list.get(0); 
		return num.intValue();
	}

}