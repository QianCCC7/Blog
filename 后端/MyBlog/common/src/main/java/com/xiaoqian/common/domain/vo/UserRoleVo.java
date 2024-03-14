package com.xiaoqian.common.domain.vo;

import com.xiaoqian.common.domain.pojo.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户角色实体")
public class UserRoleVo {
    @ApiModelProperty("用户对应的角色")
    private List<Long> roleIds;
    @ApiModelProperty("所有可用角色")
    private List<RoleVo> roles;
    @ApiModelProperty("用户信息")
    private UserDetailVo user;
}
