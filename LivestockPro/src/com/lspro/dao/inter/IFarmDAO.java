package com.lspro.dao.inter;

import java.util.List;

import org.springframework.context.annotation.Description;

import com.lspro.pojo.FarmMes;

/**
 * Description: 此接口继承了IDAO接口,以便用于功能的扩展.<br>
 * 
 * @author 阿呆
 * @see IDAO
 * @see FarmMes
 * @version 1.0
 */

public interface IFarmDAO extends IDAO<FarmMes> {

	// public List<FarmMes> findBySummary(Integer keyword,String loc) throws
	// Exception;

	/*
	 * 分割到service层当中，暂时不修改。
	 */
	public List<FarmMes> findAll(String hql, String adminLoc, String name,
			String loc, String date1, String date2, int currentPage,
			int lineSize);

	/**
	 * Description 检查养殖场的畜禽标识代码是否唯一
	 * 
	 * @param farmId
	 * @return boolean
	 * @throws Exception
	 */
	public List<FarmMes> checkId(String farmId);

	/**
	 * Description 检查动物防疫资格证编号是否唯一
	 * 
	 * @param farmId
	 * @return boolean
	 * @throws Exception
	 */
	public List<FarmMes> checkCertiId(String certifi);
	
	public String[] findName(String hql,String farmId);
}
