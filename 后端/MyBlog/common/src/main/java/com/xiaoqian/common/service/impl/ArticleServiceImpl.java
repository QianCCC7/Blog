package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.domain.pojo.Article;
import com.xiaoqian.common.mapper.ArticleMapper;
import com.xiaoqian.common.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author QianCCC
 * @since 2023-12-24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
