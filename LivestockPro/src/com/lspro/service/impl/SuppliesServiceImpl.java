package com.lspro.service.impl;

import java.io.Serializable;
import java.util.List;

import com.lspro.dao.inter.ISuppliesDAO;
import com.lspro.pojo.Supplies;
import com.lspro.pojo.SuppliesDispatch;
import com.lspro.pojo.SuppliesItem;
import com.lspro.service.SuppliesService;

public class SuppliesServiceImpl extends ServiceImpl<Supplies> implements SuppliesService {

	private ISuppliesDAO supDao;

	@Override
	public List<Supplies> findAll(String adminLoc, String supAddress,
			String name, String date1, String date2, int currentPage,
			int lineSize) {
		String hql = "select new Supplies(reserveId,address,managementstation,head,phoneNum,position,registDate,name) "
				+ "from Supplies as sup where sup.address like :adminLoc and sup.head like :name and sup.address like :supAddress "
				+ "and sup.registDate between :date1 and :date2 order by sup.registDate desc";
		List<Supplies> list = supDao.findAll(hql, adminLoc, supAddress, name,date1, date2, currentPage, lineSize);
		return list;
	}

	public List<SuppliesItem> findAllSupItem(String reserveId,
			String suppliesId, String supName, String date1, String date2,
			String checked, int currentPage, int lineSize) {
		String hql = "from SuppliesItem as sup where sup.supplies.reserveId = :reserveId and sup.failSitution = :checked and sup.suppliesId like :suppliesId and "
				+ "sup.suppliesName like :supName and sup.productDate between :date1 and :date2 order by sup.productDate desc";
		return supDao.findAllSupItem(hql, reserveId, suppliesId, supName,date1, date2, checked, currentPage, lineSize);
	}

	@Override
	public boolean checkId(String reserveId) {
		String hql = "select count(mes.reserveId) from Supplies as mes where  mes.reserveId = :reserveId group by mes.reserveId";
		List<Supplies> list = supDao.checkId(hql, reserveId);
		if (list != null && list.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean doCreateOrUpdateSupItem(SuppliesItem item) {
		this.getSupDao().doCreateOrUpdateSupItem(item);
		return true;
	}

	@Override
	public boolean doDeleteSuppliesItem(Class<SuppliesItem> entityClass,
			Serializable id) {
		this.getSupDao().doDeleteSuppliesItem(entityClass, id);
		return true;
	}

	@Override
	public SuppliesItem findSuppliesItemById(Class<SuppliesItem> entityClass,
			Serializable id) {
		return this.getSupDao().findSuppliesItemById(entityClass, id);
	}

	@Override
	public List<SuppliesDispatch> findAllSuppliesDispatch(String adminloc,
			String suppliesId, String supName, String date1, int currentPage,
			int lineSize) {
		String hql = "from SuppliesDispatch as supDis where supDis.item.supplies.address like :adminloc and supDis.item.suppliesId like :suppliesId "
				+ "and supDis.item.suppliesName like :supName and supDis.date between '1900-01-01' and :date1";
		List<SuppliesDispatch> list = this.getSupDao().findAllSuppliesDispatch(hql, adminloc, suppliesId, supName, date1, currentPage, lineSize);
		return list;
	}

	public ISuppliesDAO getSupDao() {
		return supDao;
	}

	public void setSupDao(ISuppliesDAO supDao) {
		this.supDao = supDao;
	}

	@Override
	public Supplies findAllSupplies(String id) {
		String hql = "from Supplies as sup where sup.reserveId like :id";
		Supplies list = this.getSupDao().findAllSupplies(hql, id);
		return list;
	}

	@Override
	public List findAllSuppliesName(String adminloc) {
		String hql = "select sup.reserveId,sup.address,sup.name from Supplies as sup where sup.address like :adminLoc";
		List list = this.getSupDao().findAllSuppliesName(hql, adminloc);
		return list;
	}

	@Override
	public boolean doCreateOrUpdateSupItemDispatch(SuppliesDispatch item,String id,String num) {
		String hql = "update SuppliesItem as sup set sup.number = :num where sup.suppliesId = :id";
		this.getSupDao().doCreateOrUpdateSupItemDispatch(item, hql, id, num);
		return true;
	}

	@Override
	public boolean doDeleteSuppliesItemDispatch(
			Class<SuppliesDispatch> entityClass, Serializable id) {
		this.getSupDao().doDeleteSuppliesItemDispatch(entityClass, id);
		return true;
	}

	@Override
	public SuppliesDispatch findSuppliesItemDispatchById(
			Class<SuppliesDispatch> entityClass, Serializable id) {
		return this.getSupDao().findSuppliesItemDispatchById(entityClass, id);
	}

	@Override
	public List<Supplies> findAllSuppliesForMap(String adminLoc) {
		String hql = "from Supplies as sup where sup.address like :adminLoc";
		List<Supplies> list = this.getSupDao().findAllSuppliesForMap(hql, adminLoc);
		return list;
	}

	@Override
	public boolean updateArriveStatus(boolean flag,Integer id) {
		String hql = "update SuppliesDispatch as sup set sup.isArrive = :flag where sup.id = :id ";
		this.getSupDao().updateArriveStatus(hql, flag, id);
		return true;
	}

	@Override
	public boolean batchDeleteForSupItemDis(Class<SuppliesDispatch> clazz,
			Serializable[] ids) {
		supDao.batchDeleteForSupItemDis(clazz, ids);
		return true;
	}

	@Override
	public List<SuppliesDispatch> findAllSuppliesItemDisForMap(String adminLoc,
			boolean flag) {
		String hql = "from SuppliesDispatch as supDis where supDis.item.supplies.address like :adminLoc and supDis.isArrive = :flag";
		return this.getSupDao().findAllSuppliesItemDisForMap(hql, adminLoc, flag);
	}

	@Override
	public double findCount(String address, String date1, String date2,
			int days) {
		String hql = "select count( distinct supDis.date ) from SuppliesDispatch as supDis where supDis.targerAddress like :address and supDis.date between :date1 and :date2";
		double a = supDao.findCount(hql, address, date1, date2);
		return a*1.0/days;
	}

}
