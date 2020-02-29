package me.wubc.behavior.service.impl;

import me.wubc.behavior.service.AppReadBehaviorService;
import me.wubc.common.zookeeper.sequence.Sequences;
import me.wubc.model.behavior.dtos.ReadBehaviorDto;
import me.wubc.model.behavior.pojos.ApBehaviorEntry;
import me.wubc.model.behavior.pojos.ApReadBehavior;
import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.common.enums.AppHttpCodeEnum;
import me.wubc.model.mappers.app.ApBehaviorEntryMapper;
import me.wubc.model.mappers.app.ApReadBehaviorMapper;
import me.wubc.model.user.pojos.ApUser;
import me.wubc.utils.common.BurstUtils;
import me.wubc.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wbc
 * @date 2020/02/29
 * @desc
 **/
@Service
public class AppReadBehaviorServiceImpl implements AppReadBehaviorService {
    @Autowired
    private ApReadBehaviorMapper apReadBehaviorMapper;
    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;
    @Autowired
    private Sequences sequences;

    @Override
    public ResponseResult saveReadBehavior(ReadBehaviorDto dto) {
        ApUser user = AppThreadLocalUtils.getUser();
        if (user == null && dto.getEquipmentId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        Long userId = null;
        if (user != null) {
            userId = user.getId();
        }
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(userId, dto.getEquipmentId());
        // 行为实体找以及注册了，逻辑上这里是必定有值得，除非参数错误
        if (apBehaviorEntry == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        boolean isInsert = false;
        ApReadBehavior apReadBehavior = apReadBehaviorMapper.selectByEntryId(BurstUtils.groudOne(apBehaviorEntry.getId()), apBehaviorEntry.getId(), dto.getArticleId());
        if (apReadBehavior == null) {
            apReadBehavior = new ApReadBehavior();
            apReadBehavior.setId(sequences.sequenceApReadBehavior());
            apReadBehavior.setCreatedTime(new Date());
            isInsert = true;
        }
        apReadBehavior.setArticleId(dto.getArticleId());
        apReadBehavior.setEntryId(apBehaviorEntry.getId());
        apReadBehavior.setPercentage(dto.getPercentage());
        apReadBehavior.setLoadDuration(dto.getLoadDuration());
        apReadBehavior.setUpdatedTime(new Date());
        apReadBehavior.setBurst(BurstUtils.encrypt(apReadBehavior.getId(), apReadBehavior.getId()));
        if (isInsert) {
            return ResponseResult.okResult(apReadBehaviorMapper.insert(apReadBehavior));
        } else {
            return ResponseResult.okResult(apReadBehaviorMapper.update(apReadBehavior));
        }
        
    }
}
