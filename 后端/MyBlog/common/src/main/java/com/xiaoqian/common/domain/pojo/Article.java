package com.xiaoqian.common.domain.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author QianCCC
 * @since 2023-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 文章内容
     */
    @TableField("content")
    private String content;

    /**
     * 文章摘要
     */
    @TableField("summary")
    private String summary;

    /**
     * 所属分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 缩略图
     */
    @TableField("thumbnail")
    private String thumbnail;

    /**
     * 是否置顶（0否，1是）
     */
    @TableField("is_top")
    private String isTop;

    /**
     * 状态（0已发布，1草稿）
     */
    @TableField("status")
    private String status;

    /**
     * 访问量
     */
    @TableField("view_count")
    private Long viewCount;

    /**
     * 是否允许评论 1是，0否
     */
    @TableField("is_comment")
    private String isComment;

    /**
     * 作者id
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人id
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 管理端查询博文需要标签信息
     */
    @TableField(value = "tags", exist = false)
    private List<Long> tags;
}
