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
@ApiModel("登录管理员的信息")
public class AdminUserInfo {
    @ApiModelProperty("管理员权限")
    List<String> permissions;
    @ApiModelProperty("管理员角色标识")
    List<String> roles;
    @ApiModelProperty("管理员信息")
    private LoginUserInfo user;
}
