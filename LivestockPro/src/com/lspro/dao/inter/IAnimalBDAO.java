package com.lspro.dao.inter;


import java.util.List;

import com.lspro.pojo.AnimalB;

public interface IAnimalBDAO extends IDAO<AnimalB> {
	public List<AnimalB> findAll(String hql,String role, String loc, String id,
			String shipperName,String date1,String date2, String animalSpecies,
			int currentPage, int lineSize);

	public double findCount(String hql,  String address,String date1, String date2);
}
