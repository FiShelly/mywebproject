package com.lspro.dao.inter;

import java.util.List;

import com.lspro.pojo.AdminUsers;
 
public interface IAdminUsersDAO extends IDAO<AdminUsers> {

	public List<AdminUsers> findLogin(String hql,String id,String pw,AdminUsers vo) ;
	
	public List<AdminUsers> findAll(String hql,String sAddress,String loginId,String aAddress,String date1,String date2,int currentPage, int lineSize);
	
	public int updatePw(String hql,String loginId,String pw);
	
	public List<AdminUsers> checkId(String hql,String loginId);
}
