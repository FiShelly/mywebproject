package com.teacherwork.dao;

import java.util.List;

import com.teacherwork.domain.Work;

public interface IWorkDAO extends IDAO<Work> {

	List<Work> findAllByPage(String hql,int currentPage, int lineSize,
			String tName,String tid, String itemName, int lid,int nid, int isReview) ;

	List<Work> findAllByTrem(String hql, String loginId, int ltermId,int nternId);

	int findWorkCount(String hql, String loginId, int termId, int nternId,int isReview);

	List<Work> findAllByState(String hql, String loginId, int termId,int nternId, int isReview);

	int doUpdateState(String hql, int id, String did, int state,String fbContent);


	List<Work> findAll(String hql, int termId, int nternId, int state);
}
