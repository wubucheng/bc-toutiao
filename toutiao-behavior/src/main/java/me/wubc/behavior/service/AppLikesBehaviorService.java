package me.wubc.behavior.service;

import me.wubc.model.behavior.dtos.LikesBehaviorDto;
import me.wubc.model.common.dtos.ResponseResult;

/**
 * @author wbc
 * @date 2020/02/28
 * @desc
 **/
public interface AppLikesBehaviorService {

    /**
     * 保存"喜欢"行为
     * @param dto
     * @return
     */
     ResponseResult saveLikesBehavior(LikesBehaviorDto dto);

}
