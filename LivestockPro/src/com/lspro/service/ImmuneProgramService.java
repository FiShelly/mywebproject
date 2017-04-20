package com.lspro.service;

import java.util.List;

import com.lspro.pojo.ImmunePro;



public interface ImmuneProgramService extends ServiceInter<ImmunePro>  {
	
	public boolean checkName(String name,String farmId);
	
	public List<ImmunePro> findImmunePro(String name,String farmId) ;
	
	public List<String> findSpecies(String farmId) ;
}
