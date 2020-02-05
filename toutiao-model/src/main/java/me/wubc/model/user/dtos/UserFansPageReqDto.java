package me.wubc.model.user.dtos;


import lombok.Data;
import me.wubc.model.common.dtos.PageRequestDto;

@Data
public class UserFansPageReqDto extends PageRequestDto {
    private String fansName;
    private Short level;
}
