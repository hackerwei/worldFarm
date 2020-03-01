/**
 * Java Framework Auto Generator Tools
 * Copyright (c) 2015-2020 www.wifi.com
 *
 * @company wifi
 */
package com.hz.world.core.service;

import com.hz.world.common.dto.ResultDTO;


/**
 * Title: 
 * Description: 
 * author linyanchun
 * date Mar 1, 2020 
 */
public interface CollectService {


    /**
     * 收集
     * @param userId
     * @param type 解锁类型：1:累计答错，2:累计答对，3:观看广告次数，4:投资次数，5:祭天次数，6:首充，7:解锁所有元素，8:获得指定数量金币，9:完成指定数量挑战
     * @param num
     * @return
     */
    ResultDTO<String> collect(Long userId, Integer type,String num);
    
    
    
 
  
}
