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
@ApiModel("用户表单实体")
public class UserDTO {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("手机号码")
    private String phonenumber;
    @ApiModelProperty("用户状态")
    private String status;
    @ApiModelProperty("用户性别")
    private String sex;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户头像")
    private String avatar;
    @ApiModelProperty("用户角色")
    private List<Long> roleIds;
}
