package com.lspro.service.impl;

import java.util.List;

import com.lspro.dao.inter.IProductBDAO;
import com.lspro.pojo.ProductB;
import com.lspro.service.ProductBService;

public class ProductBServiceImpl extends ServiceImpl<ProductB> implements ProductBService{
	private IProductBDAO bDao;

	@Override
	public List<ProductB> findAll(String role,String loc, String id, String shipperName,
			String date1, String date2, String productName, int currentPage,
			int lineSize) {
//		String roleChoose = "like";
//		if(role.equals("user")){
//			roleChoose = "=";
//		}
		String hql = "from ProductB as aa where aa.addressName "+(role.equals("user") ? "=" : "like") +" :addressName and (aa.id like :id and aa.shipperName like :shipperName "
				+ "and aa.productName like :productName) and aa.date between :date1 and :date2 order by aa.date desc";
		List<ProductB> list = bDao.findAll(hql, role,loc, id, shipperName, date1, date2, productName, currentPage, lineSize);
		return list;
	}
	
	public IProductBDAO getbDao() {
		return bDao;
	}

	public void setbDao(IProductBDAO bDao) {
		this.bDao = bDao;
	}

	@Override
	public double findCount(String address, String date1, String date2) {
		String hql = "select count(  aa.id )   from ProductB as aa where aa.addressName =  :addressName  and aa.date between :date1 and :date2 ";
		double aa = bDao.findCount(hql,  address,date1,date2);
		return aa;
	}


}
