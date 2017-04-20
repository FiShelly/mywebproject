package com.lspro.service.impl;

import java.util.List;

import com.lspro.dao.inter.IAnimalBDAO;
import com.lspro.pojo.AnimalB;
import com.lspro.service.AnimalBService;

public class AnimalBServiceImpl extends ServiceImpl<AnimalB> implements AnimalBService {
	
	private IAnimalBDAO bDao;

	@Override
	public List<AnimalB> findAll(String role,String id,String loc, String shipperName,
			String date1,String date2, String animalSpecies,  int currentPage, int lineSize) {
		String hql = "from AnimalB as aa where aa.startAddress "+(role.equals("user") ? "=" : "like") +" :startAddress and (aa.id like :id and aa.shipperName like :shipperName "
				+ "and aa.animalSpecies like :animalSpecies) and aa.date between :date1 and :date2 order by aa.date desc";
		List<AnimalB> list = bDao.findAll(hql,role, loc, id, shipperName, date1, date2, animalSpecies, currentPage, lineSize);
		return list;
	}
	public IAnimalBDAO getbDao() {
		return bDao;
	}
	public void setbDao(IAnimalBDAO bDao) {
		this.bDao = bDao;
	}
	@Override
	public double findCount(String address, String date1, String date2) {
		String hql = "select count( aa.id )   from AnimalB as aa where aa.startAddress =  :startAddress  and aa.date between :date1 and :date2 ";
		double aa = bDao.findCount(hql, address,date1,date2);
		return aa;
	}
}
