package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户手机号")
    private String phonenumber;
    @ApiModelProperty("用户性别")
    private String sex;
    @ApiModelProperty("用户状态")
    private String status;
    @ApiModelProperty("更细人")
    private Long updateBy;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
