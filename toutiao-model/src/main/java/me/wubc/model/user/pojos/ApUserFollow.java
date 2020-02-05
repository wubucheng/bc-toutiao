package me.wubc.model.user.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

import java.util.Date;

@Data
public class ApUserFollow {
    private Long id;
    @IdEncrypt
    private Long userId;
    @IdEncrypt
    private Integer followId;
    private String followName;
    private Short level;
    private Boolean isNotice;
    private Date createdTime;
    @JsonIgnore
    private String burst;
}