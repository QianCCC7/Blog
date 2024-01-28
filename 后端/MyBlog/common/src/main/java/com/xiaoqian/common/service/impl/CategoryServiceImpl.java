package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Category;
import com.xiaoqian.common.domain.vo.CategoryVo;
import com.xiaoqian.common.mapper.CategoryMapper;
import com.xiaoqian.common.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author QianCCC
 * @since 2024-01-17
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    /**
     * 查询文章分类列表
     */
    @Override
    public ResponseResult<List<CategoryVo>> getCategoryList() {
        Set<Integer> categoryIds = this.getBaseMapper().queryAvailableCategoryIds();
        if (!CollectionUtils.isEmpty(categoryIds)) {
            List<Category> categories = listByIds(categoryIds);
            List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
            return ResponseResult.okResult(categoryVos);
        }
        return ResponseResult.okEmptyResult();
    }
}
