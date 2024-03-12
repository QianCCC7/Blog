package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xiaoqian.common.domain.pojo.ArticleTag;
import com.xiaoqian.common.mapper.ArticleTagMapper;
import com.xiaoqian.common.service.IArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javafx.print.Collation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 管理端根据文章 id查询对应标签 id集合
     */
    @Override
    public List<Long> queryArticleTagIds(Long articleId) {
        List<ArticleTag> list = lambdaQuery()
                .eq(ArticleTag::getArticleId, articleId)
                .list();
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
    }

    /**
     * 修改文章标签关联表中文章对应的标签 id集合
     */
    @Override
    public void updateArticleTag(Long articleId, List<Long> tagIds) {
        // 3.1 先删除对应关联表
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, articleId);
        remove(queryWrapper);
        // 3.2 重新添加对应关联表
        List<ArticleTag> articleTagList = tagIds.stream()
                .map(tagId -> new ArticleTag().setArticleId(articleId).setTagId(tagId))
                .collect(Collectors.toList());
        saveBatch(articleTagList);
    }
}
