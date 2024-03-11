package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.domain.pojo.ArticleTag;
import com.xiaoqian.common.mapper.ArticleTagMapper;
import com.xiaoqian.common.service.IArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章标签关联表 服务实现类
 * </p>
 *
 * @author xiaoqian
 * @since 2024-03-11
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements IArticleTagService {

}
