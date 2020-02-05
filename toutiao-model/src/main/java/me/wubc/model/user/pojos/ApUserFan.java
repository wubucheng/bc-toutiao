package me.wubc.model.user.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

import java.util.Date;

@Data
public class ApUserFan {
    private Long id;
    @IdEncrypt
    private Integer userId;
    @IdEncrypt
    private Long fansId;
    private String fansName;
    private Short level;
    private Date createdTime;
    private Boolean isDisplay;
    private Boolean isShieldLetter;
    private Boolean isShieldComment;
    @JsonIgnore
    private String burst;
}