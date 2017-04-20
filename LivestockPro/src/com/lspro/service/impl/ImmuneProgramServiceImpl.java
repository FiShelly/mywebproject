package com.lspro.service.impl;

import java.util.List;
import com.lspro.dao.inter.IImmuneProgramDAO;
import com.lspro.pojo.ImmunePro;
import com.lspro.service.ImmuneProgramService;

public class ImmuneProgramServiceImpl extends ServiceImpl<ImmunePro> implements ImmuneProgramService {
	
	private IImmuneProgramDAO immDao;
	
	public Boolean doDeleteAboutFarm(String farmId) {
		return immDao.doDeleteAboutFarm(farmId);
	}

	@Override
	public boolean checkName(String name, String farmId)  {
		String hql = "select count(imm.name) from ImmunePro imm where imm.name = :name and imm.farm.farmId = :farmId group by imm.name";
		List<ImmunePro> list = immDao.checkName(hql,name, farmId);
		if(list != null && list.size()==1){
			return true;
		} else{
			return false;
		}
	}

	@Override
	public List<ImmunePro> findImmunePro(String name, String farmId) {
		String hql = "from ImmunePro imm where imm.name = :name and imm.farm.farmId = :farmId order by imm.sequeueNum ";
		List<ImmunePro> list = immDao.findImmunePro(hql, name, farmId);
		return list;
	}

	@Override
	public List<String> findSpecies(String farmId)  {
		String hql = "select imm.name from ImmunePro imm where  imm.farm.farmId = :farmId group by imm.name ";
		List<String> list = immDao.findSpecies(hql, farmId);
		return list;
	}

	public IImmuneProgramDAO getImmDao() {
		return immDao;
	}

	public void setImmDao(IImmuneProgramDAO immDao) {
		this.immDao = immDao;
	}
}