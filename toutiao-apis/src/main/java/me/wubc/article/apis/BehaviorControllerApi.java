package me.wubc.article.apis;

import me.wubc.model.behavior.dtos.LikesBehaviorDto;
import me.wubc.model.behavior.dtos.ReadBehaviorDto;
import me.wubc.model.behavior.dtos.ShowBehaviorDto;
import me.wubc.model.behavior.dtos.UnLikesBehaviorDto;
import me.wubc.model.common.dtos.ResponseResult;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc
 **/
public interface BehaviorControllerApi {
    /**
     * 保存用户点击文章的行为
     * @param dto
     * @return
     */
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);

    ResponseResult saveLikesBehavior(LikesBehaviorDto dto);

    ResponseResult saveUnLikesBehavior( UnLikesBehaviorDto dto) ;

    ResponseResult saveReadBehavior( ReadBehaviorDto dto);

}
