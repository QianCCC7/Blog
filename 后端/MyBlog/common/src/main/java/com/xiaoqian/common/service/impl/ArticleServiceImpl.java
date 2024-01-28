package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqian.common.constants.SystemConstants;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Article;
import com.xiaoqian.common.domain.pojo.Category;
import com.xiaoqian.common.domain.vo.ArticleDetailVo;
import com.xiaoqian.common.domain.vo.ArticleVo;
import com.xiaoqian.common.domain.vo.HotArticleVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.mapper.ArticleMapper;
import com.xiaoqian.common.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.service.ICategoryService;
import com.xiaoqian.common.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author QianCCC
 * @since 2023-12-24
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    private final ICategoryService categoryService;

    /**
     * 查询热门文章(前十条)
     */
    @Override
    @SuppressWarnings("unchecked")
    public ResponseResult<List<HotArticleVo>> hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                    .orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1, 10);// 当前为第 1页，每页显示 10条数据
        page(page, queryWrapper);// 底层调用 this.getBaseMapper().selectPage(page, queryWrapper);
        List<Article> records = page.getRecords();// 封装数据记录
        if (!CollectionUtils.isEmpty(records)) {
            return ResponseResult.okResult(BeanCopyUtils.copyBeanList(records, HotArticleVo.class));
        }
        return ResponseResult.okEmptyResult();
    }

    /**
     * 分页查询文章列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public ResponseResult<PageVo<ArticleVo>> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        // 查询条件：
        // 1. 是否根据分类 id进行查询
        // 2. 文章正常发布状态
        // 3. 文章是否置顶
        Page<Article> page = lambdaQuery()
                .eq(Objects.nonNull(categoryId) && categoryId.compareTo(0L) > 0, Article::getCategoryId, categoryId)
                .eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getIsTop)
                .page(new Page<>(pageNum, pageSize));
        List<Article> records = page.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            // 封装 categoryName
            records = records.stream()
                    .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                    .collect(Collectors.toList());
            List<ArticleVo> articleVos = BeanCopyUtils.copyBeanList(records, ArticleVo.class);
            return ResponseResult.okResult(new PageVo<>(articleVos, records.size()));
        }
        return ResponseResult.okEmptyResult();
    }

    /**
     * 获取文章详情
     */
    @Override
    public ResponseResult<ArticleDetailVo> getArticleDetailById(Long id) {
        Article article = getById(id);
        if (Objects.nonNull(article)) {
            ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
            Long categoryId = articleDetailVo.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (Objects.nonNull(category)) {
                articleDetailVo.setCategoryName(category.getName());
            }
            return ResponseResult.okResult(articleDetailVo);
        }
        return ResponseResult.okEmptyResult();
    }
}
