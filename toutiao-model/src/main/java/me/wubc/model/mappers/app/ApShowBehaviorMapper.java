package me.wubc.model.mappers.app;

import me.wubc.model.behavior.pojos.ApShowBehavior;
import me.wubc.model.mappers.CommonMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc
 **/
@Repository
public interface ApShowBehaviorMapper extends CommonMapper {

    /**
     * 通过实体ID和文章ID查询
     *
     * @param id
     * @param articleIds
     * @return
     */
    List<ApShowBehavior> selectListByEntryIdAndArticleIds(@Param("entryId") Integer id, @Param("articleIds") List articleIds);

    /**
     * 保存
     *
     * @param articleIds
     * @param entryId
     * @return
     */
    int saveShowBehavior(@Param("articleIds") List articleIds, @Param("entryId") Integer entryId);


}
