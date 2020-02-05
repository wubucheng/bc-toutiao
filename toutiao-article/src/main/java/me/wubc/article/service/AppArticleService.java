package me.wubc.article.service;

import me.wubc.model.article.dtos.ArticleHomeDto;
import me.wubc.model.common.dtos.ResponseResult;

/**
 * @author wbc
 * @date 2020/02/05
 **/
public interface AppArticleService {

    /**
     *
     * @param dto
     * @param type 加载类型：1 加载更多  2 加载更新
     * @return
     */
    ResponseResult load(ArticleHomeDto dto, Short type);

}
