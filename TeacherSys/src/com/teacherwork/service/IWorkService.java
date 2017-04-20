package com.teacherwork.service;

import java.util.List;

import com.teacherwork.domain.Work;

public interface IWorkService extends IService<Work>{
	List<Work> findAllByPage(int currentPage, int lineSize,
			String tName, String tid,String itemName, int lid,int nid, int isReview) ;
	
	List<Work> findAllByTrem(String loginId,int termId,int nternId);
	
	List<Work> findAllByState(String loginId,int termId,int nternId, int isReview);
	
	int findWorkCount(String loginId,int termId,int nternId,int isReview);
	
	boolean doUpdateState(int id,String did ,int state,String fbContent);
	
	List<Work> findAll(int termId,int nternId,int state);
	
	
}
