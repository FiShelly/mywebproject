package com.lspro.dao.inter;

/**
 * Description:
 * 此接口继承了IDAO接口,以便用于功能的扩展.<br>
 * @author 谢福成
 * @see IDAO
 * @see ProductB
 * @version 1.0
 */

import java.util.List;

import com.lspro.pojo.ProductB;

public interface IProductBDAO extends IDAO<ProductB> {

	public List<ProductB> findAll(String hql,String role, String loc, String id,
			String shipperName, String date1, String date2, String productName,
			int currentPage, int lineSize);

	public double findCount(String hql, String address, String date1, String date2);

}
