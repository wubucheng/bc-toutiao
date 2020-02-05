package me.wubc.article.service.impl;

import me.wubc.article.service.AppArticleService;
import me.wubc.common.article.constans.ArticleConstans;
import me.wubc.model.article.dtos.ArticleHomeDto;
import me.wubc.model.article.pojos.ApArticle;
import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.mappers.app.ApArticleMapper;
import me.wubc.model.mappers.app.ApUserArticleListMapper;
import me.wubc.model.user.pojos.ApUser;
import me.wubc.model.user.pojos.ApUserArticleList;
import me.wubc.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc
 **/
@Service
public class AppArticleServiceImpl implements AppArticleService {

    @Autowired
    private ApUserArticleListMapper apUserArticleListMapper;
    @Autowired
    private ApArticleMapper apArticleMapper;

    public static final Short MAX_PAGE_SIZE = 50;

    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        if (dto == null) {
            dto = new ArticleHomeDto();
        }

        Integer size = dto.getSize();
        if (size == null || size < 0) {
            size = 20;
        }

        size = Math.min(size, MAX_PAGE_SIZE);
        dto.setSize(size);

        //文章频道参数校验
        if (StringUtils.isEmpty(dto.getTag())) {
            dto.setTag(ArticleConstans.DEFAULT_TAG);
        }

        //类型参数校验
        if (!type.equals(ArticleConstans.LOADTYPE_LOAD_MORE) && !type.equals(ArticleConstans.LOADTYPE_LOAD_NEW)) {
            type = ArticleConstans.LOADTYPE_LOAD_MORE;
        }

        //获取用户的信息
        ApUser user = AppThreadLocalUtils.getUser();
        if (user != null) {
            List<ApArticle> apArticleList = this.getUserArticle(user, dto, type);
            return ResponseResult.okResult(apArticleList);
        } else {
            // 没登录，获取默认信息
            List<ApArticle> apArticleList = this.getDefaultArticle(dto, type);
            return ResponseResult.okResult(apArticleList);
        }

    }

    /**
     * 加载默认的文章信息
     *
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getDefaultArticle(ArticleHomeDto dto, Short type) {
        return apArticleMapper.loadArticleListByLocation(dto, type);
    }


    /**
     * 先从用户的推荐表中查找文章信息，如果没有再从默认文章信息获取数据
     *
     * @param user
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
        List<ApUserArticleList> list = apUserArticleListMapper.loadArticleIdListByUser(user, dto, type);
        if (!list.isEmpty()) {
            return apArticleMapper.loadArticleListByIdList(list);
        } else {
            return getDefaultArticle(dto, type);
        }
    }
}
