package com.xiaoqian.common.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户评论实体")
public class CommentDTO {
    @ApiModelProperty("文章id")
    private Long articleId;
    @ApiModelProperty("评论内容")
    private String content;
    @ApiModelProperty("评论类型(0代表文章评论，1代表友链评论)")
    private String type;
    @ApiModelProperty("根评论id")
    private Long rootId;
    @ApiModelProperty("回复目标评论id")
    private Long toCommentId;
    @ApiModelProperty("所回复的目标评论的userid")
    private Long toCommentUserId;
}
