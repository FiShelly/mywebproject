package com.lspro.service;

import java.util.List;


import com.lspro.pojo.MedicalRecord;

public interface MedicalRecordService extends ServiceInter<MedicalRecord> {
	public List<MedicalRecord> findAll(String farmId,String keyWord, int currentPage, int lineSize,String date1,String date2);
	public double findMedRate(String farmId,String date1,String date2);
}
