package me.wubc.model.behavior.dtos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;
import me.wubc.model.article.pojos.ApArticle;

import java.util.List;

@Data
public class ShowBehaviorDto {

    // 设备ID
    @IdEncrypt
    Integer equipmentId;
    List<ApArticle> articleIds;

}
