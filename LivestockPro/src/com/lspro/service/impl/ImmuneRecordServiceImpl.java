package com.lspro.service.impl;

/**
 * Desperation:
 * 姝ゆ搷浣滅被瀹炵幇浜咺ImmuneRecordDAO鎺ュ彛锛岀敤浜庡厤鐤褰曟暟鎹〃澧炲垹鏀规煡鏂规硶鐨勫疄鐜�br>
 * @author 璋㈢鎴�
 * @see IImmuneRecordDAO
 * @see ImmuneRecord
 * @version 1.0
 */

import java.util.List;

import com.lspro.dao.inter.IImmuneRecordDAO;
import com.lspro.pojo.ImmuneRecord;
import com.lspro.service.ImmuneRecordService;

public class ImmuneRecordServiceImpl extends ServiceImpl<ImmuneRecord> implements ImmuneRecordService {
	
	private IImmuneRecordDAO immDao;
	
	public Boolean doDeleteAboutFarm(String farmId)  {
		return immDao.doDeleteAboutFarm(farmId);
	}

	@Override
	public List<ImmuneRecord> findAll(String farmId, String keyWord,int currentPage, int lineSize, String date1, String date2)
		throws Exception {
		String hql = "from ImmuneRecord as immune " +
				"where immune.farm.farmId = :farmId and (immune.immuneTime like :name or immune.roomNum like :name or " +
				"immune.remainNum like :name or immune.immuneNum like :name or immune.vaccineName like :name or " +
				"immune.vaccineProducers like :name or immune.vaccineValidTime like :name or immune.batchNum like :name or " +
				"immune.immuneMethod like :name or immune.immuneDosage like :name or immune.immunePeople like :name) and " +
				"immune.immuneTime between :date1 and :date2 order by immune.immuneTime desc";
		List<ImmuneRecord> list = immDao.findAll(hql, farmId, keyWord, currentPage, lineSize, date1, date2);
		return list;
	}

	public IImmuneRecordDAO getImmDao() {
		return immDao;
	}

	public void setImmDao(IImmuneRecordDAO immDao) {
		this.immDao = immDao;
	}

	@Override
	public double findImmRate(String farmId, String date1, String date2) {
		String hql = "from ImmuneRecord as immune " +
				"where immune.farm.farmId = :farmId and immune.immuneTime between :date1 and :date2 order by immune.immuneTime desc";
		List<ImmuneRecord> list = immDao.findImmRate(hql, farmId, date1, date2);
		int remainNum = 0;
		int immNum = 0;
		for(ImmuneRecord imm : list){
			remainNum = remainNum + imm.getRemainNum();
			immNum = immNum + imm.getImmuneNum();
		}
		if(remainNum == 0){
			return 0;
		} else {
			return (immNum*1.0)/remainNum;
		}
	}
}
