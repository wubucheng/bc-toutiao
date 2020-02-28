package me.wubc.model.article.pojos;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import me.wubc.model.annotation.DateConvert;

import java.util.Date;

@ToString
@ApiModel("文章作者信息")
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