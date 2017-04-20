package com.lspro.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IFarmDAO;
import com.lspro.pojo.FarmMes;

/**
 * Desperation: 此操作类实现了IFarmDAO接口，用于养殖场基本信息增删改查方法的实现<br>
 * 
 * @author 阿呆
 * @see IFarmDAO
 * @see FarmMes
 * @version 2.0(将增删改查的close方法去掉，于调用类中使用即可，同时Transaction获取事物于每个功能方法中，而不是在构造方法,
 *          以及去掉每个功能方法中的try...catch)
 */

public class FarmDAOImpl extends DAOImpl<FarmMes> implements IFarmDAO {

	public List<FarmMes> findAll(String hql, String adminLoc, String name,
			String loc, String date1, String date2, int currentPage,
			int lineSize) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("adminLoc", adminLoc + "%")
				.setString("name", "%" + name + "%")
				.setString("loca", "%" + loc + "%").setString("date1", date1)
				.setString("date2", date2); // 分页查询
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1) * lineSize);
		query.setMaxResults(lineSize);
		List<FarmMes> list = query.list();
		return list;
	}

	public List<FarmMes> checkId(String farmId) {
		String hql = "from FarmMes mes where mes.farmId = :farmId";
		List<FarmMes> list = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("farmId", farmId).list();
		return list;
	}

	public List<FarmMes> checkCertiId(String certifi) {
		String hql = "from FarmMes mes where mes.certificate = :certifi";
		List<FarmMes> list = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("certifi", certifi).list();
		return list;
	}

	@Override
	public String[] findName(String hql,String farmId) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("farmId", farmId);
		List<Object[]> list = query.list();
		Object[] objs = list.get(0);
		return  new String[]{(String)objs[0],(String)objs[1]};
	}
}
