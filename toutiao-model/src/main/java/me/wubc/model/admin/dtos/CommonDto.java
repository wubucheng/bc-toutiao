package me.wubc.model.admin.dtos;


import lombok.Data;
import me.wubc.model.admin.enums.CommonTableEnum;

import java.util.List;

@Data
public class CommonDto {

    private Integer size;
    private Integer page;
    // 操作模式add 新增，edit编辑
    private String model;
    // 操作的对象
    private CommonTableEnum name;
    // 查询的条件
    private List<CommonWhereDto> where;
    // 修改的字段
    private List<CommonWhereDto> sets;

}
