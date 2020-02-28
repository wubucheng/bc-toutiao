package me.wubc.model.mappers.app;

import me.wubc.model.behavior.pojos.ApReadBehavior;
import org.springframework.stereotype.Repository;

/**
 * @author wbc
 * @date 2020/02/28
 * @desc
 **/
@Repository
public interface ApReadBehaviorMapper {

    int insert(ApReadBehavior record);

    int update(ApReadBehavior record);

    ApReadBehavior selectByEntryId(String burst,Integer entryId,Integer articleId);

}
