package me.wubc.article.apis;

import me.wubc.model.article.dtos.ArticleInfoDto;
import me.wubc.model.common.dtos.ResponseResult;

/**
 * @author wbc
 * @date 2020/02/23
 * @desc
 **/
public interface ArticleInfoControllerApi {
    /**
     * 加載首頁详情
     * @param dto 封装参数对象
     * @return 文章详情
     */
    ResponseResult loadArticleInfo(ArticleInfoDto dto);

    /**
     * 加载文章详情的行为内容
     * @param dto
     * @return
     */
    ResponseResult loadArticleBehavior( ArticleInfoDto dto);

}
