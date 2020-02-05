package me.wubc.model.mappers.app;

import me.wubc.model.article.dtos.ArticleHomeDto;
import me.wubc.model.article.pojos.ApArticle;
import me.wubc.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc
 **/
@Repository
public interface ApArticleMapper {
    /**
     * 根据dto条件获取文章列表
     * @param dto
     * @param type
     * @return
     */
    List<ApArticle> loadArticleListByLocation(@Param("dto") ArticleHomeDto dto, @Param("type") Short type);

    /**
     * 根据文章ID获取文章列表
     * @param list
     * @return
     */
    List<ApArticle> loadArticleListByIdList(List<ApUserArticleList> list);
}
