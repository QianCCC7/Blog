package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("标签实体")
public class TagVo {
    @ApiModelProperty("标签id")
    private Long id;
    @ApiModelProperty("标签名称")
    private String name;
    @ApiModelProperty("标签备注")
    private String remark;
}
