package com.xiaoqian.common.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("角色实体")
public class RoleDTO {
    @ApiModelProperty("角色id")
    private Long id;
    private Long roleId;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色显示顺序")
    private Integer roleSort;
    @ApiModelProperty("角色权限")
    private String roleKey;
    @ApiModelProperty("角色状态")
    private String status;
    @ApiModelProperty("角色可操作的菜单id")
    private List<Long> menuIds;
    @ApiModelProperty("角色备注")
    private String remark;
}
