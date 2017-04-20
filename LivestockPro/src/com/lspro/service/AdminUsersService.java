package com.lspro.service;

import java.util.List;

import com.lspro.pojo.AdminUsers;
 

public interface AdminUsersService extends ServiceInter<AdminUsers> {

	public boolean findLogin(AdminUsers vo) ;
	
	public boolean findLoginSuper(AdminUsers vo) ;
	
	public List<AdminUsers>  findAll(String sAddress,String loginId,String aAddress,String date1,String date2,int currentPage, int lineSize);
	
	public boolean updatePw(String loginId,String pw);
	
	public boolean checkId(String loginId);
}
