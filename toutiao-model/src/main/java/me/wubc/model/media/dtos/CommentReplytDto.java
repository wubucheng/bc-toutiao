package me.wubc.model.media.dtos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

@Data
public class CommentReplytDto {

    @IdEncrypt
    private Integer commentId;
    private String content;

}
