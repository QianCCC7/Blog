package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页数据信息")
public class PageVo<T> {
    @ApiModelProperty("分页数据记录")
    private List<T> rows;
    @ApiModelProperty("分页数据条数")
    private Integer total;
}
