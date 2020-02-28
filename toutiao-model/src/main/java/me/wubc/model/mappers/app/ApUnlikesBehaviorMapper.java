package me.wubc.model.mappers.app;

import me.wubc.model.behavior.pojos.ApUnlikesBehavior;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wbc
 * @date 2020/02/24
 * @desc
 **/
@Repository
public interface ApUnlikesBehaviorMapper {

    /**
     * 选择最后一条不喜欢数据
     *entryId behavior的ID
     * @return
     */
    ApUnlikesBehavior selectLastUnLike(@Param("entryId") Integer entryId, @Param("articleId") Integer articleId);

    int insert(ApUnlikesBehavior record);
}
