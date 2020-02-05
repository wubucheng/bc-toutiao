package me.wubc.model.article.dtos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

@Data
public class ArticleInfoDto {

    // 设备ID
    @IdEncrypt
    Integer equipmentId;
    // 文章ID
    @IdEncrypt
    Integer articleId;
    // 作者ID
    @IdEncrypt
    Integer authorId;

}
