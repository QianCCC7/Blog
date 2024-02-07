package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("评论信息")
public class CommentVo {
    @ApiModelProperty("评论id")
    private Long id;
    @ApiModelProperty("文章id")
    private Long articleId;
    @ApiModelProperty("根评论id")
    private Long rootId;
    @ApiModelProperty("评论内容")
    private String content;
    @ApiModelProperty("所回复的目标评论的 userid")
    private Long toCommentUserId;
    @ApiModelProperty("所回复的目标评论的 userName")
    private String toCommentUserName;
    @ApiModelProperty("回复目标评论id")
    private Long toCommentId;
    @ApiModelProperty("当前评论的作者id")
    private Long createBy;
    @ApiModelProperty("评论创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("当前评论的作者用户名")
    private String username;
    @ApiModelProperty("当前评论的子评论")
    private List<CommentVo> children;
}
