package com.lspro.dao.impl;

/**
 * Desperation:
 * 此操作类实现了IDisinfectionRecordDAO接口，用于消毒记录数据表基本信息增删改查方法的实现<br>
 * @author 谢福成
 * @see IDisinfectionRecordDAO
 * @see DisinfectionRecord
 * @version 2.0(去掉方法中的try...catch块)
 */

import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IDisinfectionRecordDAO;
import com.lspro.pojo.DisinfectionRecord;

public class DisinfectionRecordDAOImpl extends DAOImpl<DisinfectionRecord> implements IDisinfectionRecordDAO {

	public List<DisinfectionRecord> findAll(String hql,String farmId, String keyWord,
			int currentPage, int lineSize, String date1, String date2)  {
		 Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).
				 setString("name", "%"+keyWord+"%").setString("date1", date1).setString("date2", date2);
		 this.setRecord(query.list().size());
		 query.setFirstResult((currentPage-1)*lineSize);
		 query.setMaxResults(lineSize);
		 List<DisinfectionRecord> list = query.list();
		 return list;
	}
	 
	public Boolean doDeleteAboutFarm(String farmId)  {
		String hql = "delete DisinfectionRecord dis where dis.farm.farmId = :farmId";
		this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).executeUpdate();
		return true;
	}

	@Override
	public int findCount(String hql, String farmId,String date1, String date2) {
		 Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("farmId", farmId).
				 setString("date1", date1).setString("date2", date2);
		 List list = query.list();
		 Number num = (Number) list.get(0); 
		 return num.intValue();
	}
}
