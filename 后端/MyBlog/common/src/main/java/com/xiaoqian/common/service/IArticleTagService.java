package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.pojo.ArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章标签关联表 服务类
 * </p>
 *
 * @author xiaoqian
 * @since 2024-03-11
 */
public interface IArticleTagService extends IService<ArticleTag> {

    List<Long> queryArticleTagIds(Long id);

    void updateArticleTag(Long id, List<Long> tagIds);
}
