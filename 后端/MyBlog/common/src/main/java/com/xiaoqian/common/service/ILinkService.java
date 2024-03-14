package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.CategoryDTO;
import com.xiaoqian.common.domain.dto.LinkDTO;
import com.xiaoqian.common.domain.pojo.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.vo.CategoryVo;
import com.xiaoqian.common.domain.vo.LinkVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.query.PageQuery;

import java.util.List;

/**
 * <p>
 * 友链 服务类
 * </p>
 *
 * @author QianCCC
 * @since 2024-01-27
 */
public interface ILinkService extends IService<Link> {

    ResponseResult<List<LinkVo>> getAllLink();

    ResponseResult<PageVo<LinkVo>> queryLinkPage(PageQuery pageQuery, String name, String status);

    ResponseResult<Object> postLink(LinkDTO linkDTO);

    ResponseResult<Object> removeLink(Long id);

    ResponseResult<LinkVo> getLinkById(Long id);

    ResponseResult<Object> updateLink(LinkDTO linkDTO);

    ResponseResult<Object> updateCategoryStatus(LinkDTO linkDTO);
}
