package com.lspro.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.lspro.pojo.Supplies;
import com.lspro.pojo.SuppliesDispatch;
import com.lspro.pojo.SuppliesItem;

public interface ISuppliesDAO extends IDAO<Supplies> {
	public List<Supplies> findAll(String hql,String adminLoc,String supAddress,String name,String date1,String date2, int currentPage, int lineSize) ;
	
	public List<Supplies> checkId(String hql,String reserveId)  ;
	
	public List<SuppliesItem> findAllSupItem(String hql,String reserveId,String suppliesId,String supName,String date1,String date2,String checked,int currentPage, int lineSize);

	public void doCreateOrUpdateSupItem(SuppliesItem item);
	
	public void doDeleteSuppliesItem(Class<SuppliesItem> entityClass,Serializable id)  ;

	public SuppliesItem findSuppliesItemById(Class<SuppliesItem> entityClass,Serializable id)  ;
	
	public List<SuppliesDispatch> findAllSuppliesDispatch(String hql,String adminloc,String suppliesId, String supName,String date1,int currentPage, int lineSize);

	public Supplies findAllSupplies(String hql,String id);
	
	public List findAllSuppliesName(String hql,String adminloc);
	
	public void doCreateOrUpdateSupItemDispatch(SuppliesDispatch item,String hql,String id,String num);
	
	public void doDeleteSuppliesItemDispatch(Class<SuppliesDispatch> entityClass,Serializable id)  ;
	
	public SuppliesDispatch findSuppliesItemDispatchById(Class<SuppliesDispatch> entityClass,Serializable id)  ;
	
	public List<Supplies> findAllSuppliesForMap(String hql,String adminLoc);
	
	public void updateArriveStatus(String hql,boolean flag,Integer id);
	
	public void batchDeleteForSupItemDis(Class<SuppliesDispatch> clazz,	Serializable[] ids);
	
	public List<SuppliesDispatch> findAllSuppliesItemDisForMap(String hql,String adminLoc,boolean flag);
	
	public double findCount(String hql,String address, String date1, String date2);
}