package com.lspro.dao.impl;

/**
 * Desperation:
 * 此操作类实现了IProductADAO接口，用于动物检疫合格证明表(产品A)增删改查方法的实现<br>
 * @author 谢福成
 * @see IProductADAO
 * @see ProductA
 * @version 1.0
 */
import java.util.List;

import org.hibernate.Query;

import com.lspro.dao.inter.IProductADAO;
import com.lspro.pojo.ProductA;

public class ProductADAOImpl extends DAOImpl<ProductA> implements IProductADAO {
	@Override
	public  List<ProductA> findAll(String hql,String role, String loc, String id,
			String shipperName,String date1,String date2, String productName,
			int currentPage, int lineSize) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("id", "%" + id + "%")
				.setString("addressName",  role.equals("user")?loc: "%" + loc + "%")
				.setString("shipperName", "%" + shipperName + "%")
				.setString("date1", date1).setString("date2", date2)
				.setString("productName", "%" + productName + "%");
		this.setRecord(query.list().size());
		query.setFirstResult((currentPage - 1) * lineSize);
		query.setMaxResults(lineSize);
		List<ProductA> list = query.list();
		return list;
		
	}

	@Override
	public double findCount(String hql, String address,String date1, String date2) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql)
				.setString("addressName", address).setString("date1", date1).setString("date2", date2);
		List list = query.list();
		Number num = (Number) list.get(0); 
		return num.intValue();
	}
}
