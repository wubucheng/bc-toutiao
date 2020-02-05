package me.wubc.model.behavior.pojos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

import java.util.Date;

@Data
public class ApShareBehavior {
    private Long id;
    @IdEncrypt
    private Integer entryId;
    @IdEncrypt
    private Integer articleId;
    private Short type;
    private Date createdTime;
}