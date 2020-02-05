package me.wubc.model.article.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("首页查询")
public class ArticleHomeDto {

    // 省市
    @ApiModelProperty("省市")
    Integer provinceId;
    // 市区
    @ApiModelProperty("市区")
    Integer cityId;
    // 区县
    @ApiModelProperty("区县")
    Integer countyId;
    // 最大时间
    @ApiModelProperty("最大时间")
    Date maxBehotTime=new Date();
    // 最小时间
    @ApiModelProperty("最小时间")
    Date minBehotTime=new Date();
    // 分页size
    Integer size;
    // 数据范围，比如频道ID
    @ApiModelProperty("频道ID")
    String tag;

}
