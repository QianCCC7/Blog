package com.xiaoqian.common.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("角色实体")
public class RoleDTO {
    @ApiModelProperty("角色id")
    private Long roleId;
    @ApiModelProperty("角色状态")
    private String status;
}
