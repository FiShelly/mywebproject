package com.teacherwork.service.impl;

import java.util.List;

import com.teacherwork.dao.IWorkDAO;
import com.teacherwork.domain.Work;
import com.teacherwork.service.IWorkService;

public class WorkServiceImpl extends ServiceImpl<Work> implements IWorkService {

	private IWorkDAO wDao;
	
	@Override
	public List<Work> findAllByPage(int currentPage, int lineSize,
			String tName,String tid, String itemName, int lid,int nid, int isReview) {
		String hql = "from Work w where w.user.userName like :tName  and w.itemName like :itemName and w.term.id in(:ltermId,:ntermId) "
				+ "and w.state = :isReview ";
		if(!tid.equals("")){
			hql = hql + " and w.user.loginId = :tid";
		} 
		return this.getwDao().findAllByPage(hql, currentPage, lineSize, tName,tid, itemName,lid,nid,isReview);
	}

	public IWorkDAO getwDao() {
		return wDao;
	}

	public void setwDao(IWorkDAO wDao) {
		this.wDao = wDao;
	}

	@Override
	public List<Work> findAllByTrem(String loginId,int termId,int nternId) {
		String hql = "from Work w where w.user.loginId = :loginId and w.term.id in(:termId,:nternId)";
		return this.getwDao().findAllByTrem(hql,loginId,termId,nternId);
	}

	@Override
	public int findWorkCount(String loginId, int termId, int nternId,int isReview) {
		String hql = "select count(w.id) from Work w where w.user.loginId = :loginId and w.term.id in(:termId,:nternId) and w.state = :isReview";
		return this.getwDao().findWorkCount(hql,loginId,termId,nternId,isReview);
	}

	@Override
	public List<Work> findAllByState(String loginId, int termId, int nternId,int isReview) {
		String hql = "from Work w where w.user.loginId = :loginId and w.state = :isReview and w.term.id in(:termId,:nternId)";
		return this.getwDao().findAllByState(hql,loginId,termId,nternId,isReview);
	}

	@Override
	public boolean doUpdateState(int id,String did ,int state,String fbContent) {
		String hql = "update Work as w set w.state = :state,w.duser.loginId = :did ,w.fbContent = :fbContent where w.id = :id ";
		int count = this.getwDao().doUpdateState(hql,id,did,state,fbContent);
		if(count == 1){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Work> findAll(int termId, int nternId,int state) {
		String hql = "from Work w where  w.state = :state and w.term.id in(:termId,:nternId)";
		return this.getwDao().findAll(hql,termId,nternId,state);
	}


}
