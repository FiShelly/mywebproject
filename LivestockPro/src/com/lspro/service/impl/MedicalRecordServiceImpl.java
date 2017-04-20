package com.lspro.service.impl;

import java.util.List;

import com.lspro.dao.inter.IMedicalRecordDAO;
import com.lspro.pojo.MedicalRecord;
import com.lspro.service.MedicalRecordService;

public class MedicalRecordServiceImpl extends ServiceImpl<MedicalRecord> implements MedicalRecordService {

	private IMedicalRecordDAO imeDao ;
	
	public Boolean doDeleteAboutFarm(String farmId)  {
		return imeDao.doDeleteAboutFarm(farmId);
	}

	@Override
	public List<MedicalRecord> findAll(String farmId, String keyWord,int currentPage, int lineSize, String date1, String date2) {
		String hql ="from MedicalRecord as med " +
				"where med.farm.farmId = :farmId and (med.medicalTime like :name or med.livestockId like :name or med.roomNum like :name or " +
				"med.dateAge like :name or med.sickNum like :name or med.sickReason like :name or med.medicalPeo like :name or " +
				"med.medicalResult like :name or med.drugName like :name or med.method like :name) and " +
				"med.medicalTime between :date1 and :date2 order by med.medicalTime desc";
		List<MedicalRecord> list = this.getImeDao().findAll(hql, farmId, keyWord, currentPage, lineSize, date1, date2);
		return list;
	}

	public IMedicalRecordDAO getImeDao() {
		return imeDao;
	}

	public void setImeDao(IMedicalRecordDAO imeDao) {
		this.imeDao = imeDao;
	}

	@Override
	public double findMedRate(String farmId, String date1, String date2) {
		String hql = "from MedicalRecord as med " +
				"where med.farm.farmId = :farmId and med.medicalTime between :date1 and :date2 order by med.medicalTime desc";
		List<MedicalRecord> list = imeDao.findMedRate(hql, farmId, date1, date2);
		int medNum = 0;
		int count = 0;
		for(MedicalRecord me : list){
			count += me.getRemain();
			medNum += me.getSickNum();
		}
		if(count == 0){
			return 0;
		} else {
			return (medNum*1.0)/count;
		}
	}
}
