package com.lspro.service.impl;

import java.util.List;

import com.lspro.dao.inter.IAnimalADAO;
import com.lspro.pojo.AnimalA;
import com.lspro.service.AnimalAService;
 
public class AnimalAServiceImpl extends ServiceImpl<AnimalA> implements AnimalAService {

	private IAnimalADAO adao;
	@Override
	public List<AnimalA> findAll(String role,String id,String loc, String shipperName,
			String date1,String date2, String animalSpecies,String[] way, int currentPage, int lineSize) {
		String hql = "from AnimalA as aa where aa.startAddress "+ (role.equals("user") ? "=" : "like")  +" :startAddress and (aa.id like :id and aa.shipperName like :shipperName "
				+ "and aa.animalSpecies like :animalSpecies) and aa.date between :date1 and :date2 ";
		if(way != null){
			String temp = "";
			for(String con : way){
				temp = temp + "'" + con + "'" + ",";
			}
			hql = hql + "and aa.transportWay in ("+ temp.substring(0, temp.length()-1) +")";
		}
		hql = hql + "order by aa.date desc";
		List<AnimalA> list = adao.findAll(hql,role, loc, id, shipperName, date1, date2, animalSpecies, currentPage, lineSize);
		return list;
	}

	public IAnimalADAO getAdao() {
		return adao;
	}

	public void setAdao(IAnimalADAO adao) {
		this.adao = adao;
	}

	@Override
	public double findCount(String address, String date1, String date2) {
		String hql = "select count( aa.id )   from AnimalA as aa where aa.startAddress =  :startAddress  and aa.date between :date1 and :date2 ";
		double aa = adao.findCount(hql, address,date1,date2);
		return aa;
	}
	
}