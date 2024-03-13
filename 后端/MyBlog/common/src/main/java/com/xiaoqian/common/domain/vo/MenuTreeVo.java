package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("新增角色时需要查询菜单树实体")
public class MenuTreeVo {
    @ApiModelProperty("菜单id")
    private Long id;
    @ApiModelProperty("菜单名称")
    private String label;
    @ApiModelProperty("父菜单id")
    private Long parentId;
    @ApiModelProperty("子菜单")
    private List<MenuTreeVo> children;
}
