package me.wubc.behavior.test;

import me.wubc.behavior.BehaviorJarApplication;
import me.wubc.behavior.service.AppShowBehaviorService;
import me.wubc.model.article.pojos.ApArticle;
import me.wubc.model.behavior.dtos.ShowBehaviorDto;
import me.wubc.model.user.pojos.ApUser;
import me.wubc.utils.threadlocal.AppThreadLocalUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc
 **/
@SpringBootTest(classes = BehaviorJarApplication.class)
@RunWith(SpringRunner.class)
public class ApBehaviroTest {

    @Autowired
    private AppShowBehaviorService appShowBehaviorService;

    @Test
    public void testSave(){
        ApUser user = new ApUser();
        user.setId(123l);
        AppThreadLocalUtils.setUser(user);
        ShowBehaviorDto dto = new ShowBehaviorDto();
        List<ApArticle> list = new ArrayList<>();
        ApArticle apArticle = new ApArticle();
        apArticle.setId(200);
        list.add(apArticle);
        dto.setArticleIds(list);
        appShowBehaviorService.saveShowBehavior(dto);
    }

}
