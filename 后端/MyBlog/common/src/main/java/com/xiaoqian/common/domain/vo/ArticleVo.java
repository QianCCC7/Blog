package com.xiaoqian.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo {
    private Long id;
    // 文章标题
    private String title;
    // 文章摘要
    private String summary;
    // 文章所属分类名
    private String categoryName;
    // 文章缩略图
    private String thumbnail;
    // 文章访问量
    private Long viewCount;
    // 文章创建时间
    private LocalDateTime createTime;
}
