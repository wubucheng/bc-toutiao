package me.wubc.model.user.dtos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

@Data
public class UserRelationDto {

    // 文章作者ID
    @IdEncrypt
    Integer authorId;

    // 用户ID,作者的，无力吐槽这参数了
    @IdEncrypt
    Long userId;

    // 文章
    @IdEncrypt
    Integer articleId;
    /**
     * 操作方式
     * 0  关注
     * 1  取消
     */
    Short operation;
}
