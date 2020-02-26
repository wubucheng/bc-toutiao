package me.wubc.model.user.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

import java.util.Date;

@ApiModel("用户关注信息")
@Data
public class ApUserFollow {
    private Long id;
    @ApiModelProperty("用户ID，关注者ID")
    @IdEncrypt
    private Long userId;

    @ApiModelProperty("被关注者ID，作者ID，ps:黑马头条这项目字段命名真烂")
    @IdEncrypt
    private Integer followId;

    private String followName;
    @ApiModelProperty("关注度")
    private Short level;
    @ApiModelProperty("是否通知")
    private Boolean isNotice;
    private Date createdTime;
    @JsonIgnore
    private String burst;
}