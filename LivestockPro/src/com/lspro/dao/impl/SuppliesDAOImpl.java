package com.lspro.dao.impl;

/**
 * Desperation:
 * 此操作类实现了ISuppliesDAO接口，用于物资储备基本信息数据表增删改查方法的实现<br>
 * @author 谢福成
 * @see ISuppliesDAO
 * @see Supplies
 * @version 1.0
 */

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.ISuppliesDAO;
import com.lspro.pojo.Supplies;
import com.lspro.pojo.SuppliesDispatch;
import com.lspro.pojo.SuppliesItem;

public class SuppliesDAOImpl extends DAOImpl<Supplies> implements ISuppliesDAO {

	@Override
	public List<Supplies> findAll(String hql, String adminLoc,
			String supAddress, String name, String date1, String date2,
			int currentPage, int lineSize) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("adminLoc", adminLoc + "%")
				.setString("name", "%" + name + "%").setString("supAddress", "%" + supAddress + "%").setString("date1", date1).setString("date2", date2); // 分页查询
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1) * lineSize);
		query.setMaxResults(lineSize);
		List<Supplies> list = (List<Supplies>) query.list();
		return list;
	}

	@Override
	public List<Supplies> checkId(String hql, String reserveId) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("reserveId", reserveId);
		return query.list();
	}

	@Override
	public List<SuppliesItem> findAllSupItem(String hql, String reserveId,
			String suppliesId, String supName, String date1, String date2,
			String checked, int currentPage, int lineSize) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("reserveId", reserveId)
				.setString("suppliesId", "%" + suppliesId + "%").setString("supName", "%" + supName + "%")
				.setString("date1", date1).setString("date2", date2).setString("checked", checked);
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1) * lineSize);
		query.setMaxResults(lineSize);
		List<SuppliesItem> list = query.list();
		return list;
	}

	@Override
	public void doCreateOrUpdateSupItem(SuppliesItem item) {
		this.getSessionFactory().getCurrentSession().saveOrUpdate(item);
	}

	@Override
	public void doDeleteSuppliesItem(Class<SuppliesItem> entityClass,
			Serializable id) {
		SuppliesItem temp = this.findSuppliesItemById(entityClass, id);
		this.getSessionFactory().getCurrentSession().delete(temp);
	}

	@Override
	public SuppliesItem findSuppliesItemById(Class<SuppliesItem> entityClass,
			Serializable id) {
		SuppliesItem result = (SuppliesItem) this.getSessionFactory().getCurrentSession().get(entityClass, id);
		return result;
	}

	@Override
	public List<SuppliesDispatch> findAllSuppliesDispatch(String hql,
			String adminloc, String suppliesId, String supName, String date1,
			 int currentPage, int lineSize) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("adminloc", "%"+adminloc+"%")
				.setString("suppliesId", "%" + suppliesId + "%").setString("supName", "%" + supName + "%").setString("date1", date1);
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1) * lineSize);
		query.setMaxResults(lineSize);
		List<SuppliesDispatch> list = query.list();
		return list;
	}

	@Override
	public Supplies findAllSupplies(String hql, String id) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("id", "%"+id+"%");
		Supplies sup = (Supplies) query.list().get(0);
		return sup;
	}

	@Override
	public List findAllSuppliesName(String hql, String adminloc) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("adminLoc", "%"+adminloc+"%");
		List list = query.list();
		return list;
	}

	@Override
	public void doCreateOrUpdateSupItemDispatch(SuppliesDispatch item,String hql,String id,String num) {
		int a = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("num",num).setString("id", id).executeUpdate();
		this.getSessionFactory().getCurrentSession().saveOrUpdate(item);
	}

	@Override
	public void doDeleteSuppliesItemDispatch(Class<SuppliesDispatch> entityClass,Serializable id) {
		SuppliesDispatch temp = this.findSuppliesItemDispatchById(entityClass, id);
		temp.getItem().getSupDis().remove(temp);
		temp.setItem(null);
		this.getSessionFactory().getCurrentSession().delete(temp);
	}

	@Override
	public SuppliesDispatch findSuppliesItemDispatchById(Class<SuppliesDispatch> entityClass, Serializable id) {
		SuppliesDispatch result = (SuppliesDispatch) this.getSessionFactory().getCurrentSession().get(entityClass, id);
		return result;
	}

	@Override
	public List<Supplies> findAllSuppliesForMap(String hql, String adminLoc) {
		return this.getSessionFactory().getCurrentSession().createQuery(hql).setString("adminLoc","%"+adminLoc+"%").list();
	}

	@Override
	public void updateArriveStatus(String hql, boolean flag,Integer id) {
		getSessionFactory().getCurrentSession().createQuery(hql).setBoolean("flag", flag).setInteger("id",id).executeUpdate();
	}

	@Override
	public void batchDeleteForSupItemDis(Class<SuppliesDispatch> clazz,Serializable[] ids) {
		for(Serializable id : ids){
			doDeleteSuppliesItemDispatch(clazz, id);
		}
	}

	@Override
	public List<SuppliesDispatch> findAllSuppliesItemDisForMap(String hql,String adminLoc, boolean flag) {
		return this.getSessionFactory().getCurrentSession().createQuery(hql).setString("adminLoc","%"+adminLoc+"%").setBoolean("flag", flag).list();
	}

	@Override
	public double findCount(String hql, String address, String date1,
			String date2) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql).setString("address", address)
				.setString("date1", date1).setString("date2", date2); // 分页查询
		List list = query.list();
		Number num = (Number) list.get(0); 
		return num.intValue();
	}
}
