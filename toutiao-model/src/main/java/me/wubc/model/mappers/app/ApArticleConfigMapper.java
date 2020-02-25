package me.wubc.model.mappers.app;

import me.wubc.model.article.pojos.ApArticleConfig;
import org.springframework.stereotype.Repository;

/**
 * @author wbc
 * @date 2020/02/23
 * @desc
 **/
@Repository
public interface ApArticleConfigMapper {

    /**
     * 通过文章ID获取文章的配置信息
     *
     * @param articleId
     * @return
     */
    ApArticleConfig selectByArticleId(Integer articleId);


}
