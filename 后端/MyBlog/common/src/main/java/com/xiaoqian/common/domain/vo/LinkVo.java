package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("友链粗略信息")
public class LinkVo {
    @ApiModelProperty("友链id")
    private Long id;
    @ApiModelProperty("友链名称")
    private String name;
    @ApiModelProperty("友链logo")
    private String logo;
    @ApiModelProperty("友链描述")
    private String description;
    @ApiModelProperty("友链网站地址")
    private String address;
}
