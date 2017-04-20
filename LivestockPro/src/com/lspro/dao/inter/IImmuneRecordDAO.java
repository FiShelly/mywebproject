package com.lspro.dao.inter;

/**
 * Description:
 * 此接口继承了IDAO接口,以便用于功能的扩展.<br>
 * @author 谢福成
 * @see IDAO
 * @see ImmuneRecord
 * @version 1.0
 */

import java.util.List;



import com.lspro.pojo.ImmuneRecord;


public interface IImmuneRecordDAO extends IDAO<ImmuneRecord>{
	public List<ImmuneRecord> findAll(String hql,String farmId,String keyWord, int currentPage, int lineSize,String date1,String date2) ;
	public List<ImmuneRecord> findImmRate(String hql,String farmId,String date1,String date2);
}
