package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("登录用户的 token以及信息")
public class LoginUserVo {
    @ApiModelProperty("用户携带的 token")
    private String token;
    @ApiModelProperty("用户信息")
    private LoginUserInfo userInfo;
}
