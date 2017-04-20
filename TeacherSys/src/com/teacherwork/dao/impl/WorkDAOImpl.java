package com.teacherwork.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.teacherwork.dao.IWorkDAO;
import com.teacherwork.domain.Work;

public class WorkDAOImpl extends DAOImpl<Work> implements IWorkDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Work> findAllByPage(String hql,int currentPage, int lineSize,
			String tName, String tid,String itemName, int lid,int nid, int isReview) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("tName","%"+ tName+"%")
				.setString("itemName", "%"+itemName+"%").setInteger("ltermId", lid).setInteger("ntermId",nid).setInteger("isReview", isReview);
		if(!tid.equals("")){
			query.setString("tid",tid);
		}
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1) * lineSize);
		query.setMaxResults(lineSize);
		List<Work> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Work> findAllByTrem(String hql, String loginId, int termId,int nternId) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("loginId", loginId).setInteger("termId",termId).setInteger("nternId",nternId);
		List<Work> list = query.list();
		return list;
	}

	@Override
	public int findWorkCount(String hql, String loginId, int termId, int nternId,int isReview) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("loginId", loginId).setInteger("termId",termId).setInteger("nternId",nternId).setInteger("isReview", isReview);
		return  ((Long)query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Work> findAllByState(String hql, String loginId, int termId,
			int nternId, int isReview) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("loginId", loginId).setInteger("termId",termId).setInteger("nternId",nternId).setInteger("isReview", isReview);
		List<Work> list = query.list();
		return list; 
	}

	@Override
	public int doUpdateState(String hql, int id, String did, int state,String fbContent) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setInteger("id", id).setInteger("state",state).setString("did", did).setString("fbContent",fbContent); 
		return  query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Work> findAll(String hql, int termId, int nternId, int state) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setInteger("termId",termId).setInteger("nternId",nternId).setInteger("state",state);
		List<Work> list = query.list();
		return list;
	}
}
