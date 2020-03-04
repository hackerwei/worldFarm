/**
 * Java Framework Auto Generator Tools
 * Copyright (c) 2015-2020 www.wifi.com
 *
 * @company wifi
 */
package com.hz.world.core.service;

/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Mar 1, 2020 
 */
public interface AdService {


    /**
     * @param userId
     */
    public void addUserAd(Long userId);
    

    /**
     * @param userId
     * @return
     */
    public int getUserAdCount(Long userId);
    
    /**
     * 获取今日广告数量
     * @param userId
     * @return
     */
    public int getTodayAdCount(Long userId);
  
}
