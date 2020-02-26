package me.wubc.user.service;

import me.wubc.model.behavior.dtos.FollowBehaviorDto;
import me.wubc.model.common.dtos.ResponseResult;

/**
 * @author wbc
 * @date 2020/02/26
 * @desc
 **/
public interface AppFollowBehaviorService {

    public ResponseResult saveFollowBehavior(FollowBehaviorDto dto);


}
