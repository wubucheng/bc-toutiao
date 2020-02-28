package me.wubc.article.apis;

import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.user.dtos.UserRelationDto;

/**
 * @author wbc
 * @date 2020/02/27
 * @desc
 **/
public interface UserRelationControllerApi {
    ResponseResult follow(UserRelationDto dto);
}
