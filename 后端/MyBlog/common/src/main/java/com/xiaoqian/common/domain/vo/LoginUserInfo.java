package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel("登录用户的信息")
public class LoginUserInfo {
    @ApiModelProperty("用户 id")
    private Long id;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户头像")
    private String avatar;
    @ApiModelProperty("用户性别")
    private String sex;
    @ApiModelProperty("用户邮箱")
    private String email;
}
