package com.xiaoqian.common.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqian.common.constants.SystemConstants;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Article;
import com.xiaoqian.common.domain.vo.HotArticleVo;
import com.xiaoqian.common.mapper.ArticleMapper;
import com.xiaoqian.common.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 查询热门文章(前十条)
     * @return
     */
    @Override
    public ResponseResult<List<HotArticleVo>> hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                    .orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1, 10);// 当前为第 1页，每页显示 10条数据
        page(page, queryWrapper);// 底层调用 this.getBaseMapper().selectPage(page, queryWrapper);
        List<Article> records = page.getRecords();// 封装数据记录
        List<HotArticleVo> articleVoList = new ArrayList<>();
        for (Article article : records) {
            articleVoList.add(BeanUtil.copyProperties(article, HotArticleVo.class));
        }
        return ResponseResult.okResult(articleVoList);
    }
}
