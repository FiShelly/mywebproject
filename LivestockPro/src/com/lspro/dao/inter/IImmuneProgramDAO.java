package com.lspro.dao.inter;

import java.util.List;

import com.lspro.pojo.ImmunePro;



public interface IImmuneProgramDAO extends IDAO<ImmunePro>  {
	
	public List<ImmunePro> checkName(String hql,String name,String farmId)  ;
	
	public List<ImmunePro> findImmunePro(String hql,String name,String farmId)  ;
	
	public List<String> findSpecies(String hql,String farmId)  ;
}
