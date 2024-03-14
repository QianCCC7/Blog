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
@ApiModel("修改角色时需要查询菜单树实体")
public class RoleMenuTreeVo {
    @ApiModelProperty("角色的权限id集合")
    private List<Long> checkedKeys;
    @ApiModelProperty("角色对应的菜单")
    private List<MenuTreeVo> menus;
}
