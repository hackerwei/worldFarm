package com.hz.world.core.service;

import java.util.List;

import com.hz.world.core.domain.dto.ForeverShareElementDTO;
import com.hz.world.core.domain.dto.OtherElementDTO;
import com.hz.world.core.domain.dto.limitShareElementDTO;

public interface TargetService {


	/**
	 * 获取当日分红总金额
	 * @return
	 */
	public Double getTodayTotalShare();
	
	/**
	 * 用户限时分红小龙虾数量
	 * @param userId
	 * @return
	 */
	public int getUserLimitShareCount(Long userId);
	
	/**
	 * 获取正在进行中的限时分红小龙虾数量
	 * @param userId
	 * @return
	 */
	public List<limitShareElementDTO> getUnfinishedList(Long userId);
	
	/**
	 * 限时分红小龙虾列表
	 * @param userId
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<limitShareElementDTO> getLimitList(Long userId,Integer offset, Integer limit);
	/**
	 * 永久分红小龙虾数量
	 * @param userId
	 * @return
	 */
	public int getForeverElementCount(Long userId);
	
	/**
	 * 5洲分红小龙虾数量
	 * @param userId
	 * @return
	 */
	public int getOtherElementCount(Long userId);
	
	/**
	 * 5洲分红小龙虾
	 * @param userId
	 * @return
	 */
	public List<OtherElementDTO> getOtherElementList(Long userId);
	
	/**
	 * 永久分红小龙虾列表
	 * @return
	 */
	public List<ForeverShareElementDTO> getForeverElementList();
	
	/**
	 * 发送限时分红小龙虾
	 * @param userId
	 */
	public int addLimitShareElement(Long userId);
	
	/**
	 * 限时分红小龙虾收益
	 * @param userId
	 */
	public void limitFinishedCash(Long userId);
}
