package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqian.common.constants.RedisConstants;
import com.xiaoqian.common.constants.SystemConstants;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.ArticleDTO;
import com.xiaoqian.common.domain.pojo.Article;
import com.xiaoqian.common.domain.pojo.ArticleTag;
import com.xiaoqian.common.domain.pojo.Category;
import com.xiaoqian.common.domain.pojo.Tag;
import com.xiaoqian.common.domain.vo.*;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.exception.ArticleException;
import com.xiaoqian.common.mapper.ArticleMapper;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.service.IArticleTagService;
import com.xiaoqian.common.service.ICategoryService;
import com.xiaoqian.common.service.ITagService;
import com.xiaoqian.common.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
@SuppressWarnings({"unchecked", "rawtypes"})
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    private final ICategoryService categoryService;
    private final RedisTemplate redisTemplate;
    private final ITagService tagService;
    private final IArticleTagService articleTagService;

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
    public ResponseResult<PageVo<ArticleVo>> articleList(PageQuery pageQuery, Long categoryId) {
        // 查询条件：
        // 1. 是否根据分类 id进行查询
        // 2. 文章正常发布状态
        // 3. 文章是否置顶
        Page<Article> page = lambdaQuery()
                .eq(Objects.nonNull(categoryId) && categoryId.compareTo(0L) > 0, Article::getCategoryId, categoryId)
                .eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getIsTop)
                .page(pageQuery.toPage(pageQuery.getPageNo(), pageQuery.getPageSize()));

        List<Article> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return ResponseResult.okEmptyResult();
        }
        // 封装 categoryName
        List<ArticleVo> articleVoList = new ArrayList<>(records.size());
        for (Article article : records) {
            ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleVo.class);
            articleVo.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
            articleVoList.add(articleVo);
        }
        return ResponseResult.okResult(new PageVo<>(articleVoList, records.size()));
    }

    /**
     * 获取文章详情
     */
    @Override
    public ResponseResult<ArticleDetailVo> getArticleDetailById(Long id) {
        // 1. 查询指定文章详情
        Article article = getById(id);
        if (Objects.nonNull(article)) {
            // 2. vo封装
            ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
            // 3. 文章类别
            Long categoryId = articleDetailVo.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (Objects.nonNull(category)) {
                articleDetailVo.setCategoryName(category.getName());
            }
            // 4. 文章浏览量
            Object o = redisTemplate.opsForHash().get(RedisConstants.VIEW_COUNT_KEY, id.toString());
            Long viewCount = Long.parseLong(Objects.isNull(o) ? "0" : o.toString());
            articleDetailVo.setViewCount(viewCount);
            return ResponseResult.okResult(articleDetailVo);
        }
        return ResponseResult.okEmptyResult();
    }

    /**
     * 更新文章浏览量
     * 1. 在应用启动时把文章浏览量存储到 Redis中
     * 2. 每隔 30s把 Redis中的浏览量更新到数据库中
     * 3. 更新文章浏览量时更新 Redis中的数据
     * 4. 读取文章浏览量时读取 Redis中的数据
     */
    @Override
    public ResponseResult<Object> updateViewCount(Long id) {
        redisTemplate.opsForHash().increment(RedisConstants.VIEW_COUNT_KEY, id.toString(), 1);
        return ResponseResult.okResult();
    }

    /**
     * 写博文时需要查询所有可用的文章分类
     */
    @Override
    public ResponseResult<List<CategoryVo>> queryAllCategories() {
        List<Category> categoryList = categoryService.queryAllCategories();
        if (CollectionUtils.isEmpty(categoryList)) {
            return ResponseResult.okResult(new ArrayList<>());
        }
        return ResponseResult.okResult(BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class));
    }

    /**
     * 写博文时需要查询所有可用的文章分类
     */
    @Override
    public ResponseResult<List<TagVo>> queryAllTags() {
        List<Tag> tagList = tagService.queryAllTags();
        return ResponseResult.okResult(
                CollectionUtils.isEmpty(tagList) ?
                new ArrayList<>() : BeanCopyUtils.copyBeanList(tagList, TagVo.class));
    }

    /**
     * 新增博文
     */
    @Transactional
    @Override
    public ResponseResult<Object> postArticle(ArticleDTO articleDTO) {
        // 1. 数据校验
        if (Objects.isNull(articleDTO)) {
            throw new ArticleException(HttpCodeEnum.ARTICLE_NOT_NULL);
        }
        // 2. 属性拷贝
        Article article = BeanCopyUtils.copyBean(articleDTO, Article.class);
        // 3. 写入数据库
        save(article);
        // 4. 更新关联表
        Long articleId = article.getId();
        List<ArticleTag> articleTagList = articleDTO.getTags().stream()
                .map(tagId -> new ArticleTag().setArticleId(articleId).setTagId(tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTagList);
        return ResponseResult.okResult();
    }
}
