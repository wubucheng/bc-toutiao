package me.wubc.model.mappers.app;

import me.wubc.model.article.pojos.ApAuthor;
import org.springframework.stereotype.Repository;

/**
 * @author wbc
 * @date 2020/02/24
 * @desc
 **/
@Repository
public interface ApAuthorMapper {

    ApAuthor selectById(Integer id);



}
