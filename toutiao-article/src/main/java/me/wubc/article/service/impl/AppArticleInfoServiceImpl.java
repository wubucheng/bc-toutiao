package me.wubc.article.service.impl;

import com.google.common.collect.Maps;
import me.wubc.article.service.AppArticleInfoService;
import me.wubc.model.article.dtos.ArticleInfoDto;
import me.wubc.model.article.pojos.ApArticleConfig;
import me.wubc.model.article.pojos.ApArticleContent;
import me.wubc.model.article.pojos.ApAuthor;
import me.wubc.model.article.pojos.ApCollection;
import me.wubc.model.behavior.pojos.ApBehaviorEntry;
import me.wubc.model.behavior.pojos.ApLikesBehavior;
import me.wubc.model.behavior.pojos.ApUnlikesBehavior;
import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.common.enums.AppHttpCodeEnum;
import me.wubc.model.mappers.app.*;
import me.wubc.model.user.pojos.ApUser;
import me.wubc.model.user.pojos.ApUserFollow;
import me.wubc.utils.common.BurstUtils;
import me.wubc.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wbc
 * @date 2020/02/23
 * @desc
 **/
@Service
public class AppArticleInfoServiceImpl implements AppArticleInfoService {

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;
    @Autowired
    private ApArticleConfigMapper apArticleConfigMapper;
    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;
    @Autowired
    private ApUnlikesBehaviorMapper apUnlikesBehaviorMapper;
    @Autowired
    private ApLikesBehaviorMapper apLikesBehaviorMapper;
    @Autowired
    private ApCollectionMapper apCollectionMapper;
    @Autowired
    private ApUserFollowMapper apUserFollowMapper;
    @Autowired
    private ApAuthorMapper apAuthorMapper;

    /**
     * 获取文章信息
     * 1、校验参数
     * 2、获取配置信息
     * 2.1 配置信息不为空，则判断文章是否被删除了，没有删除，则查询文章内容
     * 3、范湖数据
     *
     * @param articleId
     * @return
     */
    @Override
    public ResponseResult getArticleInfo(Integer articleId) {
        if (articleId == null || articleId < 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        Map<String, Object> data = new HashedMap();

        ApArticleConfig apArticleConfig = apArticleConfigMapper.selectByArticleId(articleId);
        if (apArticleConfig == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        } else if (!apArticleConfig.getIsDelete()) {
            // 没删除，则获取文章内容
            ApArticleContent apArticleContent = apArticleContentMapper.selectByArticleId(articleId);
            data.put("content", apArticleContent);
        }

        data.put("config", apArticleConfig);
        return ResponseResult.okResult(data);
    }

    @Override
    public ResponseResult loadArticleBehavior(ArticleInfoDto dto) {
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

        boolean isUnLike = false;
        boolean isLike = false;
        boolean isCollect = false;
        boolean isFlow = false;
        boolean isCollection = false;
        boolean isFollow = false;
        String burst = BurstUtils.groudOne(apBehaviorEntry.getId());

        //  判断是否不喜欢
        ApUnlikesBehavior apUnlikesBehavior = apUnlikesBehaviorMapper.selectLastUnLike(apBehaviorEntry.getId(), dto.getArticleId());
        if (apUnlikesBehavior != null && apUnlikesBehavior.getType() == ApUnlikesBehavior.Type.UNLIKE.getCode()) {
            isUnLike = true;
        }

        // 判断是否喜欢
        ApLikesBehavior apLikesBehavior = apLikesBehaviorMapper.selectLastLike(burst, apBehaviorEntry.getId(), dto.getArticleId(), ApCollection.Type.ARTICLE.getCode());
        if (apLikesBehavior != null && apLikesBehavior.getOperation() == ApLikesBehavior.Operation.LIKE.getCode()) {
            isLike = true;
        }

        // 判断是否收藏
        ApCollection apCollection = apCollectionMapper.selectForEntryId(burst, apBehaviorEntry.getId(), dto.getArticleId(), ApCollection.Type.ARTICLE.getCode());
        if (apCollection != null) {
            isCollection = true;
        }

        // 判断是否关注
        ApAuthor apAuthor = apAuthorMapper.selectById(dto.getAuthorId());
        if (user != null && apAuthor != null && apAuthor.getUserId() != null) {
            // 查找当前用户跟作者之间的关系
            ApUserFollow apUserFollow = apUserFollowMapper.selectByFollowId(BurstUtils.groudOne(user.getId()), user.getId(), apAuthor.getUserId().intValue());
            if (apUserFollow != null) {
                isFollow = true;
            }
        }

        Map<String, Object> data = Maps.newHashMap();
        data.put("isfollow", isFollow);
        data.put("islike", isLike);
        data.put("isunlike", isUnLike);
        data.put("iscollection", isCollection);

        return ResponseResult.okResult(data);

    }
}
