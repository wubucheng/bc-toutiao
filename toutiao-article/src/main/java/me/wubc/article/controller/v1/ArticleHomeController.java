package me.wubc.article.controller.v1;

import me.wubc.article.apis.ArticleHomeControllerApi;
import me.wubc.article.service.AppArticleService;
import me.wubc.common.article.constans.ArticleConstans;
import me.wubc.model.article.dtos.ArticleHomeDto;
import me.wubc.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wbc
 * @date 2020/02/05
 **/
@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController implements ArticleHomeControllerApi {

    @Autowired
    private AppArticleService appArticleService;

    @Override
    @GetMapping("/load")
    public ResponseResult load(ArticleHomeDto dto) {
        return appArticleService.load(dto, ArticleConstans.LOADTYPE_LOAD_MORE);
    }

    @Override
    @GetMapping("/loadMore")
    public ResponseResult loadMore(ArticleHomeDto dto) {
        return appArticleService.load(dto, ArticleConstans.LOADTYPE_LOAD_MORE);
    }

    @Override
    @GetMapping("/loadNew")
    public ResponseResult loadNew(ArticleHomeDto dto) {
        return appArticleService.load(dto, ArticleConstans.LOADTYPE_LOAD_NEW);
    }
}
