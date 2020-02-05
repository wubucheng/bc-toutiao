package me.wubc.behavior.service;

import me.wubc.model.behavior.dtos.ShowBehaviorDto;
import me.wubc.model.common.dtos.ResponseResult;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc
 **/
public interface AppShowBehaviorService {

    ResponseResult saveShowBehavior(ShowBehaviorDto dto);

}
