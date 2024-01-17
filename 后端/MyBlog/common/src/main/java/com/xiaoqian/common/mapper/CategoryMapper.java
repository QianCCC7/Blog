package com.xiaoqian.common.mapper;

import com.xiaoqian.common.domain.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Set;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author QianCCC
 * @since 2024-01-17
 */
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 查询有效的分类列表，即对应的分类下有已发布的文章且该分类可用
     */
    Set<Integer> queryAvailableCategoryIds();
}
