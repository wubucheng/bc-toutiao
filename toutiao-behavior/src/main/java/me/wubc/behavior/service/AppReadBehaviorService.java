package me.wubc.behavior.service;

import me.wubc.model.behavior.dtos.ReadBehaviorDto;
import me.wubc.model.common.dtos.ResponseResult;

/**
 * @author wbc
 * @date 2020/02/29
 * @desc
 **/
public interface AppReadBehaviorService {
    /**
     * 存储阅读数据
     *
     * @param dto
     * @return
     */
    ResponseResult saveReadBehavior(ReadBehaviorDto dto);

}
