package me.wubc.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.wubc.common.zookeeper.sequence.Sequences;
import me.wubc.model.article.pojos.ApAuthor;
import me.wubc.model.behavior.dtos.FollowBehaviorDto;
import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.common.enums.AppHttpCodeEnum;
import me.wubc.model.mappers.app.ApAuthorMapper;
import me.wubc.model.mappers.app.ApUserFanMapper;
import me.wubc.model.mappers.app.ApUserFollowMapper;
import me.wubc.model.mappers.app.ApUserMapper;
import me.wubc.model.user.dtos.UserRelationDto;
import me.wubc.model.user.pojos.ApUser;
import me.wubc.model.user.pojos.ApUserFan;
import me.wubc.model.user.pojos.ApUserFollow;
import me.wubc.user.service.AppFollowBehaviorService;
import me.wubc.user.service.AppUserRelationService;
import me.wubc.utils.common.BurstUtils;
import me.wubc.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wbc
 * @date 2020/02/26
 * @desc
 **/
@Slf4j
@Service
public class AppUserRelationServiceImpl implements AppUserRelationService {

    @Autowired
    ApUserFollowMapper apUserFollowMapper;
    @Autowired
    ApUserFanMapper apUserFanMapper;
    @Autowired
    ApAuthorMapper apAuthorMapper;
    @Autowired
    ApUserMapper apUserMapper;
    @Autowired
    AppFollowBehaviorService appFollowBehaviorService;
    @Autowired
    Sequences sequences;

    @Override
    public ResponseResult follow(UserRelationDto dto) {
        if (dto.getOperation() == null || dto.getOperation() < 0 || dto.getOperation() > 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "operation参数无效");
        }
        Long followId = dto.getUserId();
        if (followId == null && dto.getAuthorId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "followId和authorId无效");
        } else if (followId == null) {
            ApAuthor apAuthor = apAuthorMapper.selectById(dto.getAuthorId());
            if (apAuthor != null) {
                followId = apAuthor.getUserId();
            }
        }

        // 再次判断要关注的人是否存在
        if (followId == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "要关注的人不存在");
        } else {
            ApUser user = AppThreadLocalUtils.getUser();
            if (user != null) {
                Short operation = dto.getOperation();
                if ("0".equals(operation)) {
                    // 0 表示要关注
                } else if ("1".equals(operation)) {
                    // 1 表示取消关注
                }
            } else {
                return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            }
        }

        return null;
    }


    private ResponseResult followByUserId(ApUser user, Integer followId, Integer articleId) {
        ApUser followUser = apUserMapper.selectById(followId);
        if (followUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "关注用户不存在");
        }

        ApUserFollow apUserFollow = apUserFollowMapper.selectByFollowId(BurstUtils.groudOne(user.getId()), user.getId(), followId);
        if (apUserFollow == null) {
            // 不存在关注记录
            ApUserFan apUserFan = apUserFanMapper.selectByFansId(BurstUtils.groudOne(followId), followId, user.getId());
            if (apUserFan == null) {
                apUserFan = new ApUserFan();
                apUserFan.setId(sequences.sequenceApUserFan());
                apUserFan.setUserId(followId);
                apUserFan.setFansId(user.getId());
                apUserFan.setFansName(user.getName());
                apUserFan.setLevel((short) 0);
                apUserFan.setIsDisplay(true);
                apUserFan.setIsShieldComment(false);
                apUserFan.setIsShieldLetter(false);
                apUserFan.setBurst(BurstUtils.encrypt(apUserFan.getId(), apUserFan.getUserId()));

                apUserFanMapper.insert(apUserFan);
            }

            //相互关注？
            // 谁关注我
            apUserFollow = new ApUserFollow();
            apUserFollow.setId(sequences.sequenceApUserFollow());
            apUserFollow.setFollowId(followId);
            apUserFollow.setFollowName(followUser.getName());
            apUserFollow.setCreatedTime(new Date());
            apUserFollow.setLevel((short) 0);
            apUserFollow.setIsNotice(true);
            apUserFollow.setBurst(BurstUtils.encrypt(apUserFollow.getId(), apUserFollow.getFollowId()));

            //新增关注行为
            FollowBehaviorDto followBehaviorDto = new FollowBehaviorDto();
            followBehaviorDto.setFollowId(followId);
            followBehaviorDto.setArticleId(articleId);
            appFollowBehaviorService.saveFollowBehavior(followBehaviorDto);
            return ResponseResult.okResult(apUserFollowMapper.insert(apUserFollow));

        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST, "已关注");
        }
    }
}
