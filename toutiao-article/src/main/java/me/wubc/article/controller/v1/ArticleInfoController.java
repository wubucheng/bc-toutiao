package me.wubc.article.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wubc.article.apis.ArticleInfoControllerApi;
import me.wubc.article.service.AppArticleInfoService;
import me.wubc.model.article.dtos.ArticleInfoDto;
import me.wubc.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wbc
 * @date 2020/02/23
 * @desc
 **/
@Api("文章内容与行为接口")
@RestController
@RequestMapping("/api/v1/article")
public class ArticleInfoController implements ArticleInfoControllerApi {

    @Autowired
    private AppArticleInfoService appArticleInfoService;

    @ApiOperation("加载文章信息")
    @Override
    @PostMapping("/loadArticleInfo")
    public ResponseResult loadArticleInfo(@RequestBody ArticleInfoDto dto) {
        return appArticleInfoService.getArticleInfo(dto.getArticleId());
    }


    @ApiOperation("加载文章行为")
    @Override
    @PostMapping("/loadArticleBehavior")
    public ResponseResult loadArticleBehavior(@RequestBody ArticleInfoDto dto) {
        return appArticleInfoService.loadArticleBehavior(dto);
    }
}
