package me.wubc.user.service;

import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.user.dtos.UserRelationDto;

/**
 * @author wbc
 * @date 2020/02/26
 * @desc
 **/
public interface AppUserRelationService {
    ResponseResult follow(UserRelationDto dto);
}
