package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.vo.ArticleDetailVo;
import com.xiaoqian.common.domain.vo.ArticleVo;
import com.xiaoqian.common.domain.vo.HotArticleVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.query.PageQuery;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author QianCCC
 * @since 2023-12-24
 */
public interface IArticleService extends IService<Article> {

    ResponseResult<List<HotArticleVo>> hotArticleList();

    ResponseResult<PageVo<ArticleVo>> articleList(PageQuery pageQuery, Long categoryId);

    ResponseResult<ArticleDetailVo> getArticleDetailById(Long id);

    ResponseResult<Object> updateViewCount(Long id);
}
