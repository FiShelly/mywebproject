package com.lspro.service;

import java.util.List;

import com.lspro.pojo.AnimalA;

public interface AnimalAService extends ServiceInter<AnimalA> {
	public List<AnimalA> findAll(String role,String id, String loc, String shipperName,
			String date1,String date2, String animalSpecies,String[] way, int currentPage, int lineSize);
	
	public double findCount(String address,String date1,String date2);
}
