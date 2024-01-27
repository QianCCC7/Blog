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
@ApiModel("文章详情信息")
public class ArticleDetailVo {
    @ApiModelProperty("文章id")
    private Long id;
    @ApiModelProperty("文章标题")
    private String title;
    @ApiModelProperty("文章内容")
    private String content;
    @ApiModelProperty("文章分类")
    private String categoryName;
    @ApiModelProperty("文章分类id")
    private Long categoryId;
    @ApiModelProperty("文章缩略图")
    private String thumbnail;
    @ApiModelProperty("文章访问量")
    private Long viewCount;
    @ApiModelProperty("文章创建时间")
    private LocalDateTime createTime;
}
