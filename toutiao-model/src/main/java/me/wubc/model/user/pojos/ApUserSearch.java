package me.wubc.model.user.pojos;

import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

import java.util.Date;

@Data
public class ApUserSearch {
    private Integer id;
    @IdEncrypt
    private Integer entryId;
    private String keyword;
    private Integer status;
    private Date createdTime;

}