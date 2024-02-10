package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户信息")
public class UserDetailVo {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户头像")
    private String avatar;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户性别")
    private String sex;
}
