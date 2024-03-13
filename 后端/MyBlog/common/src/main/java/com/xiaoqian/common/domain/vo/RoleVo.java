package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("角色相关信息")
public class RoleVo {
    @ApiModelProperty("角色id")
    private Long id;
    @ApiModelProperty("角色权限")
    private String roleKey;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("显示顺序")
    private Integer roleSort;
    @ApiModelProperty("角色状态")
    private String status;
}
