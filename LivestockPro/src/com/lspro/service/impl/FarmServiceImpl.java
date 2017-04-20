package com.lspro.service.impl;

import java.util.List;

import com.lspro.dao.inter.IFarmDAO;
import com.lspro.pojo.FarmMes;
import com.lspro.service.FarmService;

public class FarmServiceImpl extends ServiceImpl<FarmMes> implements
		FarmService {

	private IFarmDAO farmDao;

	public List<FarmMes> findAll(String adminLoc, String name, String loc,
			String date1, String date2, int currentPage, int lineSize) {
		String hql = "from FarmMes as farm where (farm.location like :adminLoc) and farm.farmName like :name and farm.location like :loca and "
				+ "farm.registDate between :date1 and :date2";
		List<FarmMes> list = this.getFarmDao().findAll(hql, adminLoc, name,
				loc, date1, date2, currentPage, lineSize);
		return list;
	}

	public boolean checkId(String farmId) {
		List<FarmMes> list = this.getFarmDao().checkId(farmId);
		if (list != null && list.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkCertiId(String certifi) {
		List<FarmMes> list = this.getFarmDao().checkCertiId(certifi);
		if (list != null && list.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	public IFarmDAO getFarmDao() {
		return farmDao;
	}

	public void setFarmDao(IFarmDAO farmDao) {
		this.farmDao = farmDao;
	}

	@Override
	public String[] findName(String farmId) {
		String hql = "select farm.farmName,farm.registDate from FarmMes as farm where  farm.farmId = :farmId";
		return farmDao.findName(hql, farmId);
	}
}
