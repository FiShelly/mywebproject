package com.lspro.service;

import java.util.List;
import com.lspro.pojo.FarmMes;
public interface FarmService extends ServiceInter<FarmMes>{
	
	public List<FarmMes> findAll(String adminLoc,String name,String loc,String date1,String date2, int currentPage, int lineSize);

	public boolean checkId(String farmId);
	
	public boolean checkCertiId(String certifi);
	
	public String[] findName(String farmId);
}
