package me.wubc.model.mappers.app;

import me.wubc.model.behavior.pojos.ApBehaviorEntry;
import me.wubc.model.mappers.CommonMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc
 **/
@Repository
public interface ApBehaviorEntryMapper extends CommonMapper {

    /**
     * 通过用户ID或设备ID查找行为实体
     *
     * @param userId
     * @param equipmentId
     * @return
     */
    ApBehaviorEntry selectByUserIdOrEquipemntId(@Param("userId") Long userId, @Param("equipmentId") Integer equipmentId);

}
