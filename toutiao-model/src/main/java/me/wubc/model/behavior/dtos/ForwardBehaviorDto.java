package me.wubc.model.behavior.dtos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

@Data
public class ForwardBehaviorDto {
    // 设备ID
    @IdEncrypt
    Integer equipmentId;
    // 文章ID
    @IdEncrypt
    Integer articleId;
    @IdEncrypt
    Integer dynamicId;
}
