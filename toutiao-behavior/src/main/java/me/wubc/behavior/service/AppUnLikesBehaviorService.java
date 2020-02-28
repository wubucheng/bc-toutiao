package me.wubc.behavior.service;

import me.wubc.model.behavior.dtos.UnLikesBehaviorDto;
import me.wubc.model.common.dtos.ResponseResult;

/**
 * @author wbc
 * @date 2020/02/28
 * @desc
 **/
public interface AppUnLikesBehaviorService {
    ResponseResult saveUnLikesBehavior(UnLikesBehaviorDto dto);
}
