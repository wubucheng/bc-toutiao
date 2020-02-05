package me.wubc.model.media.pojos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

import java.util.Date;

@Data
public class WmMaterial {
    private Integer id;
    @IdEncrypt
    private Long userId;
    private String url;
    private short type;
    private Short isCollection;
    private Date createdTime;
}