package com.lspro.dao.inter;

import java.util.List;

import com.lspro.pojo.Users;

public interface IUsersDAO extends IDAO<Users> {

	public List<Users> findLogin(String hql,Users users)  ;
	
	public Users findByFarmId(String hql,String farmId)  ;
	
	public List<Users> checkId(String hql,String loginId)  ;
}
