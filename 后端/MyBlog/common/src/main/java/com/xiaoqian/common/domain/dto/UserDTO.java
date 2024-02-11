package com.xiaoqian.common.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户更新信息表单实体")
public class UserDTO {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户性别")
    private String sex;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("用户头像")
    private String avatar;
}
