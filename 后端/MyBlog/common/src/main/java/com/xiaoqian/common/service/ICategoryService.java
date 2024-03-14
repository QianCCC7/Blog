package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.vo.CategoryVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.query.PageQuery;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author QianCCC
 * @since 2024-01-17
 */
public interface ICategoryService extends IService<Category> {

    ResponseResult<List<CategoryVo>> getCategoryList();

    List<Category> queryAllCategories();

    void exportExcel(HttpServletResponse response);

    ResponseResult<PageVo<CategoryVo>> queryCategoryPage(PageQuery pageQuery, String name, String status);
}
