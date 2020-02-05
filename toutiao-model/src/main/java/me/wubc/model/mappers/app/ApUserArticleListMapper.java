package me.wubc.model.mappers.app;

import me.wubc.model.article.dtos.ArticleHomeDto;
import me.wubc.model.user.pojos.ApUser;
import me.wubc.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc 文章用户推荐表
 **/
@Repository
public interface ApUserArticleListMapper {
    /**
     * 根据用户ID获取文章推荐信息
     * @param user
     * @param dto
     * @param type
     * @return
     */
    List<ApUserArticleList> loadArticleIdListByUser(@Param("user") ApUser user, @Param("dto") ArticleHomeDto dto, @Param("type") Short type);
}

