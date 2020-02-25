package me.wubc.model.mappers.app;

import me.wubc.model.article.pojos.ApArticleContent;
import org.springframework.stereotype.Repository;

/**
 * @author wbc
 * @date 2020/02/23
 * @desc
 **/
@Repository
public interface ApArticleContentMapper {

    /**
     * 根据ID获取文章内容
     *
     * @param articleId
     * @return
     */
    ApArticleContent selectByArticleId(Integer articleId);

}
