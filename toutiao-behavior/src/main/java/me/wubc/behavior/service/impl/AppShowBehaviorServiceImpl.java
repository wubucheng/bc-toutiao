package me.wubc.behavior.service.impl;

import me.wubc.behavior.service.AppShowBehaviorService;
import me.wubc.model.article.pojos.ApArticle;
import me.wubc.model.behavior.dtos.ShowBehaviorDto;
import me.wubc.model.behavior.pojos.ApBehaviorEntry;
import me.wubc.model.behavior.pojos.ApShowBehavior;
import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.common.enums.AppHttpCodeEnum;
import me.wubc.model.mappers.app.ApBehaviorEntryMapper;
import me.wubc.model.mappers.app.ApShowBehaviorMapper;
import me.wubc.model.user.pojos.ApUser;
import me.wubc.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc
 **/

@Service
public class AppShowBehaviorServiceImpl implements AppShowBehaviorService {
    Logger logger = LoggerFactory.getLogger(AppShowBehaviorServiceImpl.class);

    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;
    @Autowired
    private ApShowBehaviorMapper apShowBehaviorMapper;

    /**
     * 保存用户行为
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult saveShowBehavior(ShowBehaviorDto dto) {
        logger.info("请求参数："+dto);
        ApUser user = AppThreadLocalUtils.getUser();
        logger.info("user: "+user);
        Integer equipmentId = dto.getEquipmentId();
        if (user == null && equipmentId == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        Long userId = null;
        if (user != null) {
            userId = user.getId();
        }

        // 根据设备ID或用户ID查询是否是用户行为数据
        logger.info("执行查询");
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(userId, equipmentId);
        if (apBehaviorEntry == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        List<Integer> articleIds = dto.getArticleIds().stream()
                .map(ApArticle::getId)
                .collect(Collectors.toList());
        List<ApShowBehavior> apShowBehaviors = apShowBehaviorMapper.selectListByEntryIdAndArticleIds(apBehaviorEntry.getId(), articleIds);
        // 过滤掉已存在的文章ID
        if (CollectionUtils.isNotEmpty(apShowBehaviors)) {
            apShowBehaviors.stream().forEach(item -> {
                Integer articleId = item.getArticleId();
                articleIds.remove(articleId);
            });
        }

        if (CollectionUtils.isNotEmpty(articleIds)) {
            // 这里应该是apBehaviorEntry.getEntityId()才对？
            apShowBehaviorMapper.saveShowBehavior(articleIds, apBehaviorEntry.getId());
        }


        return null;
    }
}
