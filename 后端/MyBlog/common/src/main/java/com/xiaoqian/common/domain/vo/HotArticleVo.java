package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("热门文章粗略信息")
public class HotArticleVo {
    @ApiModelProperty("热门文章id")
    private Long id;
    @ApiModelProperty("热门文章标题")
    private String title;
    @ApiModelProperty("热门文章浏览量")
    private Long viewCount;
}
