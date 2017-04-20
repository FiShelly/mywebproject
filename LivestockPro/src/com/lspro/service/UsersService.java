package com.lspro.service;

import com.lspro.pojo.Users;

public interface UsersService extends ServiceInter<Users> {

	public boolean findLogin(Users users) ;
	
	public Users findByFarmId(String farmId) ;
	
	public boolean checkId(String loginId) ;
}
