package me.wubc.article.test;

import me.wubc.article.ArticleJarApplication;
import me.wubc.article.service.AppArticleService;
import me.wubc.common.article.constans.ArticleConstans;
import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.user.pojos.ApUser;
import me.wubc.utils.threadlocal.AppThreadLocalUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc
 **/
@SpringBootTest(classes = ArticleJarApplication.class)
@RunWith(SpringRunner.class)
public class ArticleTest {
    @Autowired
    private AppArticleService appArticleService;

    @Test
    public void testArticle() {
        ApUser apUser = new ApUser();
        apUser.setId(123l);
        AppThreadLocalUtils.setUser(apUser);
        ResponseResult result = appArticleService.load(null, ArticleConstans.LOADTYPE_LOAD_MORE);
        System.out.println(result.getData());
    }
}
