package me.wubc.behavior.service.impl;

import me.wubc.behavior.service.AppUnLikesBehaviorService;
import me.wubc.common.zookeeper.sequence.Sequences;
import me.wubc.model.behavior.dtos.UnLikesBehaviorDto;
import me.wubc.model.behavior.pojos.ApBehaviorEntry;
import me.wubc.model.behavior.pojos.ApUnlikesBehavior;
import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.common.enums.AppHttpCodeEnum;
import me.wubc.model.mappers.app.ApBehaviorEntryMapper;
import me.wubc.model.mappers.app.ApUnlikesBehaviorMapper;
import me.wubc.model.user.pojos.ApUser;
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
public class AppUnLikesBehaviorServiceImpl implements AppUnLikesBehaviorService {
    @Autowired
    private ApUnlikesBehaviorMapper apUnlikesBehaviorMapper;
    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;
    @Autowired
    private Sequences sequences;

    /**
     * 保存"不喜欢"行为
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult saveUnLikesBehavior(UnLikesBehaviorDto dto) {
        ApUser user = AppThreadLocalUtils.getUser();
        if (user == null && dto.getEquipmentId() == null) {
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

        ApUnlikesBehavior apUnlikesBehavior = new ApUnlikesBehavior();
        apUnlikesBehavior.setArticleId(dto.getArticleId());
        apUnlikesBehavior.setCreatedTime(new Date());
        apUnlikesBehavior.setEntryId(apBehaviorEntry.getId());
        apUnlikesBehavior.setType(dto.getType());
        return ResponseResult.okResult(apUnlikesBehaviorMapper.insert(apUnlikesBehavior));
    }
}

