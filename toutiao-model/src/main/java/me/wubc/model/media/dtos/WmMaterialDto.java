package me.wubc.model.media.dtos;


import lombok.Data;
import me.wubc.model.annotation.IdEncrypt;

@Data
public class WmMaterialDto {

    @IdEncrypt
    private Integer id;

//    private String url;
}
