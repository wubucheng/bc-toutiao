package me.wubc.model.article.pojos;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

@ApiModel("文章内容")
@Data
public class ApArticleContent {
    private Integer id;
    // 增加注解，JSON序列化时自动混淆加密
    @IdEncrypt
    private Integer articleId;
    private String content;

}