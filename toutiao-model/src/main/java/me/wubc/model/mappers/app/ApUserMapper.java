package me.wubc.model.mappers.app;

import me.wubc.model.user.pojos.ApUser;
import org.springframework.stereotype.Repository;

/**
 * @author wbc
 * @date 2020/02/26
 * @desc
 **/
@Repository
public interface ApUserMapper {

    ApUser selectById(Integer id);


}
