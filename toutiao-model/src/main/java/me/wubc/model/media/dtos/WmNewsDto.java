package me.wubc.model.media.dtos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

import java.util.Date;
import java.util.List;

@Data
public class WmNewsDto {
    private Integer id;
    private String title;
    @IdEncrypt
    private Integer channelId;
    private String labels;
    private Date publishTime;
    private String content;
    private Short type;
    private Date submitedTime;
    private Short status;
    private String reason;
    private List<String> images;
}
