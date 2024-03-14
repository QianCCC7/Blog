package com.xiaoqian.common.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqian.common.constants.SystemConstants;
import com.xiaoqian.common.domain.CategoryExcel;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.CategoryDTO;
import com.xiaoqian.common.domain.pojo.Category;
import com.xiaoqian.common.domain.vo.CategoryVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.mapper.CategoryMapper;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.utils.BeanCopyUtils;
import com.xiaoqian.common.utils.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    /**
     * 写博文时需要查询所有可用的文章分类
     */
    @Override
    public List<Category> queryAllCategories() {
        List<Category> categoryList = lambdaQuery().eq(Category::getStatus, SystemConstants.CATEGORY_STATUS_NORMAL).list();
        return CollectionUtils.isEmpty(categoryList) ? new ArrayList<>() : categoryList;
    }

    /**
     * 导出excel
     */
    @Override
    public void exportExcel(HttpServletResponse response) {
        try {
            // 1. 设置下载文件的请求头
            WebUtils.setExcelDownloadHeader("分类.xlsx", response);
            // 2. 获取需要导出的数据
            List<Category> categoryList = list();
            List<CategoryExcel> categoryExcelList = BeanCopyUtils.copyBeanList(categoryList, CategoryExcel.class);
            // 3. 将数据写入excel
            EasyExcel.write(response.getOutputStream(), CategoryExcel.class)
                    .autoCloseStream(Boolean.FALSE) // 设置不关闭流
                    .sheet("分类导出")
                    .doWrite(categoryExcelList);
        } catch (Exception e) {
            response.reset();// 重置 response，清空数据
            ResponseResult<Object> result = ResponseResult.errorResult(HttpCodeEnum.EXCEL_DOWNLOAD_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    /**
     * 分页查询分类列表
     */
    @Override
    public ResponseResult<PageVo<CategoryVo>> queryCategoryPage(PageQuery pageQuery, String name, String status) {
        Page<Category> page = lambdaQuery()
                .like(StringUtils.hasText(name), Category::getName, name)
                .eq(StringUtils.hasText(status), Category::getStatus, status)
                .page(pageQuery.toPage(pageQuery.getPageNo(), pageQuery.getPageSize()));
        List<Category> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return ResponseResult.okEmptyResult();
        }
        List<CategoryVo> categoryVoList = BeanCopyUtils.copyBeanList(records, CategoryVo.class);
        return ResponseResult.okResult(new PageVo<>(categoryVoList, records.size()));
    }

    /**
     * 新增分类
     */
    @Override
    public ResponseResult<Object> postCategory(CategoryDTO categoryDTO) {
        Category category = BeanCopyUtils.copyBean(categoryDTO, Category.class);
        save(category);
        return ResponseResult.okResult();
    }

    /**
     * 删除分类
     */
    @Override
    public ResponseResult<Object> removeCategory(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

    /**
     * 修改分类信息前需要先查询分类信息
     */
    @Override
    public ResponseResult<CategoryVo> getCategoryById(Long id) {
        Category category = getById(id);
        return ResponseResult.okResult(BeanCopyUtils.copyBean(category, CategoryVo.class));
    }

    /**
     * 修改分类信息
     */
    @Override
    public ResponseResult<Object> updateCategory(CategoryDTO categoryDTO) {
        Category category = BeanCopyUtils.copyBean(categoryDTO, Category.class);
        updateById(category);
        return ResponseResult.okResult();
    }
}
