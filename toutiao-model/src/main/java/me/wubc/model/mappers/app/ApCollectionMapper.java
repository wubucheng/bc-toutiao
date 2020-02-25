package me.wubc.model.mappers.app;

import me.wubc.model.article.pojos.ApCollection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wbc
 * @date 2020/02/23
 * @desc
 **/
@Repository
public interface ApCollectionMapper {

    /**
     * 选择一个终端的收藏数据
     * behavior_entry_id 指的是设备ID或用户ID
     * entryId 指的是文章ID
     *
     * @return
     */
    ApCollection selectForEntryId(@Param("burst") String burst, @Param("objectId") Integer objectId, @Param("entryId") Integer entryId, @Param("type") Short type);


}
