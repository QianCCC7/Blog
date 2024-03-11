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
@ApiModel("博文实体")
public class ArticleDTO {
    @ApiModelProperty("文章标题")
    private String title;
    @ApiModelProperty("文章内容")
    private String content;
    @ApiModelProperty("文章摘要")
    private String summary;
    @ApiModelProperty("所属分类id")
    private Long categoryId;
    @ApiModelProperty("缩略图")
    private String thumbnail;
    @ApiModelProperty("是否置顶（0否，1是")
    private String isTop;
    @ApiModelProperty("状态（0已发布，1草稿）")
    private String status;
    @ApiModelProperty("访问量")
    private Long viewCount;
    @ApiModelProperty("是否允许评论 1是，0否")
    private String isComment;
    @ApiModelProperty("文章关联标签的id集合")
    private List<Long> tags;
}
