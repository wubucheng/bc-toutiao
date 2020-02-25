package me.wubc.model.mappers.app;

import me.wubc.model.behavior.pojos.ApLikesBehavior;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wbc
 * @date 2020/02/24
 * @desc
 **/

@Repository
public interface ApLikesBehaviorMapper {

    ApLikesBehavior selectLastLike(@Param("burst") String burst,@Param("objectId") Integer objectId,@Param("articleId") Integer articleId, @Param("type") Short type);


}
