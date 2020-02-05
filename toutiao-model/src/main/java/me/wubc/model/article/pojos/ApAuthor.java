package me.wubc.model.article.pojos;

import lombok.Data;
import me.wubc.model.annotation.DateConvert;

import java.util.Date;

@Data
public class ApAuthor {
    private Integer id;
    private String name;
    private Integer type;
    // APP社交账号
    private Long userId;
    // 自媒体管理账号
    private Long wmUserId;
    @DateConvert("yyyyMMddHHmmss")
    private Date createdTime;
}