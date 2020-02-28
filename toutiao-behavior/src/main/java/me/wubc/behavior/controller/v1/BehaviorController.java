package me.wubc.behavior.controller.v1;

import me.wubc.article.apis.BehaviorControllerApi;
import me.wubc.behavior.service.AppLikesBehaviorService;
import me.wubc.behavior.service.AppShowBehaviorService;
import me.wubc.behavior.service.AppUnLikesBehaviorService;
import me.wubc.model.behavior.dtos.LikesBehaviorDto;
import me.wubc.model.behavior.dtos.ShowBehaviorDto;
import me.wubc.model.behavior.dtos.UnLikesBehaviorDto;
import me.wubc.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wbc
 * @date 2020/02/05
 * @desc 记录用户行为
 **/
@RestController
@RequestMapping("/api/v1/behavior")
public class BehaviorController implements BehaviorControllerApi {

    @Autowired
    private AppShowBehaviorService appShowBehaviorService;
    @Autowired
    private AppLikesBehaviorService appLikesBehaviorService;
    @Autowired
    private AppUnLikesBehaviorService appUnLikesBehaviorService;

    @Override
    @PostMapping("/saveBehavior")
    public ResponseResult saveShowBehavior(@RequestBody ShowBehaviorDto dto) {
        return appShowBehaviorService.saveShowBehavior(dto);
    }

    @Override
    @PostMapping("/likeBehavior")
    public ResponseResult saveLikesBehavior(LikesBehaviorDto dto) {
        return appLikesBehaviorService.saveLikesBehavior(dto);
    }

    @Override
    @PostMapping("/unlikeBehavior")
    public ResponseResult saveUnLikesBehavior(UnLikesBehaviorDto dto) {
        return appUnLikesBehaviorService.saveUnLikesBehavior(dto);
    }
}
