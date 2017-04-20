package com.lspro.service.impl;

import com.lspro.dao.inter.IDisinfectionRecordDAO;
import com.lspro.dao.inter.IDisposalHarmlessDAO;
import com.lspro.dao.inter.IEpidemicMonitoringDAO;
import com.lspro.dao.inter.IFarmDAO;
import com.lspro.dao.inter.IFoodDrugUseRecordDAO;
import com.lspro.dao.inter.IImmuneProgramDAO;
import com.lspro.dao.inter.IImmuneRecordDAO;
import com.lspro.dao.inter.IMedicalRecordDAO;
import com.lspro.dao.inter.IProductRecordDAO;
import com.lspro.dao.inter.IUsersDAO;
import com.lspro.pojo.CountMsg;
import com.lspro.pojo.FarmMes;
import com.lspro.service.BackMoreService;

public class BackMoreServiceImpl implements BackMoreService {

	private IDisinfectionRecordDAO disinDao;
	private IDisposalHarmlessDAO dispoDao ;
	private IEpidemicMonitoringDAO epiDao ;
	private IFoodDrugUseRecordDAO foodDao ;
	private IImmuneRecordDAO immunDao;
	private IImmuneProgramDAO immproDao;
	private IMedicalRecordDAO medDao ;
	private IProductRecordDAO proDao;
	private IUsersDAO userDao ;
	private IFarmDAO farmDao;
	
	public BackMoreServiceImpl() {
	}
	
	public boolean cascadeDelete(String farmId)  {
		boolean flag = false;
		flag = disinDao.doDeleteAboutFarm(farmId);
		flag = dispoDao.doDeleteAboutFarm(farmId);
		flag = epiDao.doDeleteAboutFarm(farmId);
		flag = foodDao.doDeleteAboutFarm(farmId);
		flag = immunDao.doDeleteAboutFarm(farmId);
		flag = medDao.doDeleteAboutFarm(farmId);
		flag = proDao.doDeleteAboutFarm(farmId);
		flag = immproDao.doDeleteAboutFarm(farmId);
		flag = userDao.doDeleteAboutFarm(farmId);
		farmDao.doDelete(FarmMes.class, farmId);
		return flag;
	}

	public IDisinfectionRecordDAO getDisinDao() {
		return disinDao;
	}

	public void setDisinDao(IDisinfectionRecordDAO disinDao) {
		this.disinDao = disinDao;
	}

	public IDisposalHarmlessDAO getDispoDao() {
		return dispoDao;
	}

	public void setDispoDao(IDisposalHarmlessDAO dispoDao) {
		this.dispoDao = dispoDao;
	}

	public IEpidemicMonitoringDAO getEpiDao() {
		return epiDao;
	}

	public void setEpiDao(IEpidemicMonitoringDAO epiDao) {
		this.epiDao = epiDao;
	}

	public IFoodDrugUseRecordDAO getFoodDao() {
		return foodDao;
	}

	public void setFoodDao(IFoodDrugUseRecordDAO foodDao) {
		this.foodDao = foodDao;
	}

	public IImmuneRecordDAO getImmunDao() {
		return immunDao;
	}

	public void setImmunDao(IImmuneRecordDAO immunDao) {
		this.immunDao = immunDao;
	}

	public IMedicalRecordDAO getMedDao() {
		return medDao;
	}

	public void setMedDao(IMedicalRecordDAO medDao) {
		this.medDao = medDao;
	}

	public IProductRecordDAO getProDao() {
		return proDao;
	}

	public void setProDao(IProductRecordDAO proDao) {
		this.proDao = proDao;
	}

	public IUsersDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(IUsersDAO userDao) {
		this.userDao = userDao;
	}

	public IImmuneProgramDAO getImmproDao() {
		return immproDao;
	}

	public void setImmproDao(IImmuneProgramDAO immproDao) {
		this.immproDao = immproDao;
	}

	public IFarmDAO getFarmDao() {
		return farmDao;
	}

	public void setFarmDao(IFarmDAO farmDao) {
		this.farmDao = farmDao;
	}
}
