package me.wubc.model.article.pojos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

@Data
public class ApArticleConfig {
    private Long id;
    // 增加注解，JSON序列化时自动混淆加密
//    @JsonIgnore
    @IdEncrypt
    private Integer articleId;
    private Boolean isComment;
    private Boolean isForward;
    private Boolean isDown;
    private Boolean isDelete;
}