package com.lspro.service;


/**
 * Description:
 * 姝ゆ帴鍙ｇ户鎵夸簡IDAO鎺ュ彛,浠ヤ究鐢ㄤ簬鍔熻兘鐨勬墿灞�<br>
 * @author 璋㈢鎴�
 * @see IDAO
 * @see AnimalB
 * @version 1.0
 */
import com.lspro.pojo.AnimalB;

import java.util.List;

public interface AnimalBService extends ServiceInter<AnimalB> {
	public List<AnimalB> findAll(String role,String id, String loc, String shipperName,
			String date1,String date2, String animalSpecies, int currentPage, int lineSize);
	
	public double findCount(String address,String date1,String date2);
}
