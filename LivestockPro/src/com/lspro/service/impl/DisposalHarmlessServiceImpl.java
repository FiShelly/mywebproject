package com.lspro.service.impl;


import java.util.List;

import com.lspro.dao.inter.IDisposalHarmlessDAO;
import com.lspro.pojo.DisposalHarmless;
import com.lspro.service.DisposalHarmlessService;

public class DisposalHarmlessServiceImpl extends ServiceImpl<DisposalHarmless> implements DisposalHarmlessService {
	
	private IDisposalHarmlessDAO dispoDao ;
	
	public Boolean doDeleteAboutFarm(String farmId)  {
		return dispoDao.doDeleteAboutFarm(farmId);
	}

	@Override
	public List<DisposalHarmless> findAll(String farmId, String keyWord,int currentPage, int lineSize, String date1, String date2){
		String hql ="from DisposalHarmless as disp " +
					"where disp.farm.farmId = :farmId and (disp.disposalTime like :name or disp.number like :name or " +
					"disp.disposalOrResult like :name or disp.farm.farmId like :name or disp.disposalMethod like :name or " +
					"disp.disposalStation like :name or disp.note like :name) and " +
					"disp.disposalTime between :date1 and :date2 order by disp.disposalTime desc";
		List<DisposalHarmless> list = this.getDispoDao().findAll(hql, farmId, keyWord, currentPage, lineSize, date1, date2);
		return list;
	}

	public IDisposalHarmlessDAO getDispoDao() {
		return dispoDao;
	}

	public void setDispoDao(IDisposalHarmlessDAO dispoDao) {
		this.dispoDao = dispoDao;
	}

//	@Override
//	public double findDeathAllNum(String farmId, String date1, String date2) {
//		String hql ="from DisposalHarmless as disp " +
//				"where disp.farm.farmId = :farmId and disp.disposalTime between :date1 and :date2 order by disp.disposalTime desc";
//		List<DisposalHarmless> list = dispoDao.findDeathAllNum(hql, farmId, date1, date2);
//		int count = 0;
//		for(DisposalHarmless dis : list) {
//			count += dis.getNumber();
//		}
//		return count;
//	}

	
}
