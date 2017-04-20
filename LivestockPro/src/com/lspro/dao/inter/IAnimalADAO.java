package com.lspro.dao.inter;

/**
 * Description:
 * 此接口继承了IDAO接口,以便用于功能的扩展.<br>
 * @author 谢福成
 * @see IDAO
 * @see AnimalA
 * @version 1.0
 */

import java.util.List;

import com.lspro.pojo.AnimalA;

public interface IAnimalADAO extends IDAO<AnimalA> {
	public List<AnimalA> findAll(String hql,String role, String loc, String id,
			String shipperName,String date1,String date2, String animalSpecies,
			int currentPage, int lineSize);

	public double findCount(String hql, String address, String date1, String date2);
}
