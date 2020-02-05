package me.wubc.model.media.dtos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;
import me.wubc.model.common.dtos.PageRequestDto;

import java.util.Date;

@Data
public class WmNewsPageReqDto extends PageRequestDto {

    private Short status;
    private Date beginPubdate;
    private Date endPubdate;
    @IdEncrypt
    private Integer channelId;
    private String keyWord;
}
