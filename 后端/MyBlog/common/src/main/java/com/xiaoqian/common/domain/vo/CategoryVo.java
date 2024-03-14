package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分类信息")
public class CategoryVo {
    @ApiModelProperty("分类id")
    private Long id;
    @ApiModelProperty("分类名称")
    private String name;
    @ApiModelProperty("分类状态")
    private String status;
    @ApiModelProperty("分类描述")
    private String description;
    @ApiModelProperty("父分类id")
    private Long pid;
}
