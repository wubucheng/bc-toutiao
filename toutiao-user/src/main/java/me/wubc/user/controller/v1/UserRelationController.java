package me.wubc.user.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wubc.article.apis.UserRelationControllerApi;
import me.wubc.model.common.dtos.ResponseResult;
import me.wubc.model.user.dtos.UserRelationDto;
import me.wubc.user.service.AppUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wbc
 * @date 2020/02/27
 * @desc
 **/
@Api
@RestController
@RequestMapping("api/v1/user")
public class UserRelationController implements UserRelationControllerApi {

    @Autowired
    private AppUserRelationService appUserRelationService;

    @ApiOperation("关注操作")
    @Override
    @PostMapping("/userFollow")
    public ResponseResult follow(@RequestBody UserRelationDto dto) {
        return appUserRelationService.follow(dto);
    }

}
