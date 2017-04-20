package com.lspro.service.impl;

import java.util.List;

import com.lspro.dao.inter.IProductADAO;
import com.lspro.pojo.ProductA;
import com.lspro.service.ProductAService;

public class ProductAServiceImpl extends ServiceImpl<ProductA> implements ProductAService {

	private IProductADAO aDao;
	
	@Override
	public List<ProductA> findAll(String role,String loc, String id, String shipperName,
			String date1, String date2, String productName, String[] way,int currentPage,
			int lineSize) {
		String hql = "from ProductA as aa where aa.addressName "+ (role.equals("user") ? "=" : "like") +" :addressName and (aa.id like :id or aa.shipperName like :shipperName "
				+ "or aa.productName like :productName) and aa.date between :date1 and :date2 ";
		if(way != null){
			String temp = "";
			for(String con : way){
				temp = temp + "'" + con + "'" + ",";
			}
			hql = hql + "and aa.transportWay in ("+ temp.substring(0, temp.length()-1) +")";
		}
		hql = hql + "order by aa.date desc";
		
		List<ProductA> list = aDao.findAll(hql,role,loc, id, shipperName, date1, date2, productName, currentPage, lineSize);
		return list;
	}

	public IProductADAO getaDao() {
		return aDao;
	}

	public void setaDao(IProductADAO aDao) {
		this.aDao = aDao;
	}

	@Override
	public double findCount(String address, String date1, String date2) {
		String hql = "select count( aa.id )   from ProductA as aa where aa.addressName =  :addressName  and aa.date between :date1 and :date2 ";
		double aa = aDao.findCount(hql,address,date1,date2);
		return aa;
	}

}
