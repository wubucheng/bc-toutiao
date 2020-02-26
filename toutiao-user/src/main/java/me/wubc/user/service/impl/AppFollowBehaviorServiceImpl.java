package me.wubc.user.service.impl;

import me.wubc.common.zookeeper.sequence.Sequences;
import me.wubc.model.behavior.dtos.FollowBehaviorDto;
import me.wubc.model.behavior.pojos.ApBehaviorEntry;
import me.wubc.model.behavior.pojos.ApFollowBehavior;
import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.common.enums.AppHttpCodeEnum;
import me.wubc.model.mappers.app.ApBehaviorEntryMapper;
import me.wubc.model.mappers.app.ApFollowBehaviorMapper;
import me.wubc.model.user.pojos.ApUser;
import me.wubc.user.service.AppFollowBehaviorService;
import me.wubc.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wbc
 * @date 2020/02/26
 * @desc 关注行为
 **/
@SuppressWarnings("all")
@Service
public class AppFollowBehaviorServiceImpl implements AppFollowBehaviorService {

    @Autowired
    private ApFollowBehaviorMapper apFollowBehaviorMapper;
    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;
    @Autowired
    private Sequences sequences;

    @Async
    @Override
    public ResponseResult saveFollowBehavior(FollowBehaviorDto dto) {
        ApUser user = AppThreadLocalUtils.getUser();
        if (user == null && dto.getEquipmentId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }

        Long userId = null;
        if (user != null) {
            userId = user.getId();
        }

        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(userId, dto.getEquipmentId());
        if (apBehaviorEntry == null) {
            // 需保证已经点进去过文章了
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        ApFollowBehavior apFollowBehavior = new ApFollowBehavior();
        apFollowBehavior.setEntryId(apBehaviorEntry.getId());
        apFollowBehavior.setArticleId(dto.getArticleId());
        apFollowBehavior.setFollowId(dto.getFollowId());
        apFollowBehavior.setCreatedTime(new Date());
        apFollowBehavior.setId(sequences.sequenceApUserFan());
        return ResponseResult.okResult(apFollowBehaviorMapper.insert(apFollowBehavior));
    }


}
