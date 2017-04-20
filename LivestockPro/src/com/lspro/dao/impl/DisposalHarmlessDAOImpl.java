package com.lspro.dao.impl;

/**
 * Desperation:
 * 此操作类实现了IDisposalHarmlessDAO接口，用于消毒记录数据表基本信息增删改查方法的实现<br>
 * @author 谢福成
 * @see IDisposalHarmlessDAO
 * @see DisposalHarmless
 * @version 1.0
 */

import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IDisposalHarmlessDAO;
import com.lspro.pojo.DisposalHarmless;

public class DisposalHarmlessDAOImpl extends DAOImpl<DisposalHarmless>
		implements IDisposalHarmlessDAO {

	public Boolean doDeleteAboutFarm(String farmId) {
		String hql = "delete DisposalHarmless dis where dis.farm.farmId = :farmId";
		this.getSessionFactory().getCurrentSession().createQuery(hql)
				.setString("farmId", farmId).executeUpdate();
		return true;
	}

	@Override
	public List<DisposalHarmless> findAll(String hql, String farmId,
			String keyWord, int currentPage, int lineSize, String date1,
			String date2) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("farmId", farmId)
				.setString("name", "%" + keyWord + "%")
				.setString("date1", date1).setString("date2", date2);
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1) * lineSize);
		query.setMaxResults(lineSize);
		List<DisposalHarmless> list = query.list();
		return list;
	}

//	@Override
//	public List<DisposalHarmless> findDeathAllNum(String hql, String farmId,
//			String date1, String date2) {
//		Query query = this.getSessionFactory().getCurrentSession()
//				.createQuery(hql).setString("farmId", farmId)
//				.setString("date1", date1).setString("date2", date2);
//		List<DisposalHarmless> list = query.list();
//		return list;
//	}

}
