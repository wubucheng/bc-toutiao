package me.wubc.model.media.dtos;


import lombok.Data;
import me.wubc.model.common.dtos.PageRequestDto;

@Data
public class WmMaterialListDto extends PageRequestDto {
    Short isCollected; //1 查询收藏的
}
