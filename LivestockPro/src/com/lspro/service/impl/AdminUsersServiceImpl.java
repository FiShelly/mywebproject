package com.lspro.service.impl;

import java.util.List;

import com.lspro.dao.inter.IAdminUsersDAO;
import com.lspro.pojo.AdminUsers;
import com.lspro.service.AdminUsersService;

public class AdminUsersServiceImpl extends ServiceImpl<AdminUsers> implements AdminUsersService {

	private IAdminUsersDAO userDao;
	
	public boolean findLogin(AdminUsers vo) {
		String loginHql = "from AdminUsers user where user.loginId = :id and user.password = :pw";
		List<AdminUsers> list = userDao.findLogin(loginHql, "id", "pw", vo);
		if(list != null && list.size()==1){
			return true;
		} else {
			return false;
		}
	}

	public IAdminUsersDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(IAdminUsersDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<AdminUsers> findAll(String sAddress, String loginId,String aAddress, String date1, String date2,int currentPage, int lineSize) {
		System.out.println(sAddress + "="+loginId + "=" + aAddress);
		String hql = "from AdminUsers as ad where ad.address like :sAddress and ad.loginId like :loginId and ad.address like :aAddress "
				+ "and ad.registDate between :date1 and :date2 order by ad.address desc";
		return userDao.findAll(hql, sAddress, loginId, aAddress, date1, date2, currentPage,  lineSize);
	}

	@Override
	public boolean updatePw(String loginId, String pw) {
		String hql = "update  AdminUsers as ad set ad.password = :pw where ad.loginId = :loginId";
		if(userDao.updatePw(hql, loginId, pw) == 1){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkId(String loginId) {
		String hql = "from AdminUsers as ad where ad.loginId = :loginId";
		List<AdminUsers> list = userDao.checkId(hql, loginId);
		if (list != null && list.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean findLoginSuper(AdminUsers vo) {
		String loginHql = "from AdminUsers user where user.loginId = :id and user.password = :pw and user.isSuperAdmin = true";
		List<AdminUsers> list = userDao.findLogin(loginHql, "id", "pw", vo);
		if(list != null && list.size()==1){
			return true;
		} else {
			return false;
		}
	}

}
