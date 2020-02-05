package me.wubc.model.user.dtos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

@Data
public class FansOperationDto {
    @IdEncrypt
    private Integer fansId;

    /**
     * true 开   false 关
     */
    private Boolean switchState;
}
