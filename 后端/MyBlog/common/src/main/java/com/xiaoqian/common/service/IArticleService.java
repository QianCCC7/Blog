package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author QianCCC
 * @since 2023-12-24
 */
public interface IArticleService extends IService<Article> {

    ResponseResult hotArticleList();
}
