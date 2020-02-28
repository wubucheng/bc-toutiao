package me.wubc.behavior.service.impl;

import me.wubc.behavior.service.AppLikesBehaviorService;
import me.wubc.common.zookeeper.sequence.Sequences;
import me.wubc.model.behavior.dtos.LikesBehaviorDto;
import me.wubc.model.behavior.pojos.ApBehaviorEntry;
import me.wubc.model.behavior.pojos.ApLikesBehavior;
import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.common.enums.AppHttpCodeEnum;
import me.wubc.model.mappers.app.ApBehaviorEntryMapper;
import me.wubc.model.mappers.app.ApLikesBehaviorMapper;
import me.wubc.model.user.pojos.ApUser;
import me.wubc.utils.common.BurstUtils;
import me.wubc.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wbc
 * @date 2020/02/28
 * @desc
 **/
@Service
public class AppLikesBehaviorServiceImpl implements AppLikesBehaviorService {

    @Autowired
    private ApLikesBehaviorMapper apLikesBehaviorMapper;
    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;
    @Autowired
    private Sequences sequences;


    /**
     * 保存"喜欢"行为
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult saveLikesBehavior(LikesBehaviorDto dto) {
        ApUser user = AppThreadLocalUtils.getUser();
        if (dto.getEquipmentId() == null && user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        Long userId = null;
        if (user != null) {
            userId = user.getId();
        }
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(userId, dto.getEquipmentId());
        if (apBehaviorEntry == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        ApLikesBehavior apLikesBehavior = new ApLikesBehavior();
        apLikesBehavior.setId(sequences.sequenceApLikes());
        apLikesBehavior.setBehaviorEntryId(apBehaviorEntry.getId());
        apLikesBehavior.setBurst(BurstUtils.encrypt(apLikesBehavior.getId(), apLikesBehavior.getBehaviorEntryId()));
        apLikesBehavior.setCreatedTime(new Date());
        apLikesBehavior.setEntryId(dto.getEntryId());
        apLikesBehavior.setOperation(dto.getOperation());
        apLikesBehavior.setType(dto.getType());

        return ResponseResult.okResult(apLikesBehaviorMapper.insert(apLikesBehavior));
    }
}
