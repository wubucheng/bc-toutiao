package me.wubc.model.mappers.app;

import me.wubc.model.user.pojos.ApUserFan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wbc
 * @date 2020/02/25
 * @desc 用户粉丝（被关注）:谁关注我
 **/
@Repository
public interface ApUserFanMapper {

    int insert(ApUserFan record);

    ApUserFan selectByFansId(@Param("burst") String burst, @Param("userId") Integer userId, @Param("fansId") Long fansId);

    int deleteByFansId(@Param("burst") String burst, @Param("userId") Integer userId, @Param("fansId") Long fansId);

}
