package me.wubc.model.mappers.app;

import me.wubc.model.user.pojos.ApUserFollow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wbc
 * @date 2020/02/23
 * @desc 用户关注表（关注的人）
 **/
@Repository
public interface ApUserFollowMapper {

    /*
    查找是否有关注信息
     */
    ApUserFollow selectByFollowId(
            @Param("burst") String burst, @Param("userId") Long userId, @Param("followId") Integer followId);


    int insert(ApUserFollow record);;

    int deleteByFollowId( @Param("burst") String burst, @Param("userId") Long userId, @Param("followId") Integer followId);
}
