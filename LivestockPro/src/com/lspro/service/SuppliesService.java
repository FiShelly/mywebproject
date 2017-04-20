package com.lspro.service;

import java.io.Serializable;
import java.util.List;

import com.lspro.pojo.Supplies;
import com.lspro.pojo.SuppliesDispatch;
import com.lspro.pojo.SuppliesItem;

public interface SuppliesService extends ServiceInter<Supplies> {
	public List<Supplies> findAll(String adminLoc, String supAddress,String name, String date1, String date2, int currentPage,int lineSize);

	public List<SuppliesItem> findAllSupItem(String reserveId,String suppliesId, String supName, String date1, String date2,String checked, int currentPage, int lineSize);

	public boolean checkId(String reserveId);
	
	public boolean doCreateOrUpdateSupItem(SuppliesItem item);
	
	public boolean doDeleteSuppliesItem(Class<SuppliesItem> entityClass,Serializable id)  ;

	public SuppliesItem findSuppliesItemById(Class<SuppliesItem> entityClass,Serializable id)  ;
	
	public List<SuppliesDispatch> findAllSuppliesDispatch(String adminloc,String suppliesId, String supName,String date1,int currentPage, int lineSize);

	public Supplies findAllSupplies(String id);
	
	public List findAllSuppliesName(String adminloc);

	public boolean doCreateOrUpdateSupItemDispatch(SuppliesDispatch item,String num,String id);
	
	public boolean doDeleteSuppliesItemDispatch(Class<SuppliesDispatch> entityClass,Serializable id)  ;
	
	public SuppliesDispatch findSuppliesItemDispatchById(Class<SuppliesDispatch> entityClass,Serializable id)  ;
	
	public List<Supplies> findAllSuppliesForMap(String adminLoc);
	
	public boolean updateArriveStatus(boolean flag,Integer id);
	
	public boolean batchDeleteForSupItemDis(Class<SuppliesDispatch> clazz,Serializable[] ids);
	
	public List<SuppliesDispatch> findAllSuppliesItemDisForMap(String adminLoc, boolean flag);
	
	public double findCount(String address,String date1,String date2,int days);
}