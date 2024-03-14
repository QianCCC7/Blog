package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqian.common.constants.SystemConstants;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.LinkDTO;
import com.xiaoqian.common.domain.pojo.Link;
import com.xiaoqian.common.domain.vo.LinkVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.mapper.LinkMapper;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.ILinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 友链 服务实现类
 * </p>
 *
 * @author QianCCC
 * @since 2024-01-27
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {

    /**
     * 查询所有审核通过的友链列表
     */
    @Override
    public ResponseResult<List<LinkVo>> getAllLink() {
        List<Link> linkList = lambdaQuery()
                .eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL)
                .list();
        if (!CollectionUtils.isEmpty(linkList)) {
            List<LinkVo> linkVoList = BeanCopyUtils.copyBeanList(linkList, LinkVo.class);
            return ResponseResult.okResult(linkVoList);
        }
        return ResponseResult.okEmptyResult();
    }

    /**
     * 分页查询友链列表
     */
    @Override
    public ResponseResult<PageVo<LinkVo>> queryLinkPage(PageQuery pageQuery, String name, String status) {
        Page<Link> page = lambdaQuery()
                .like(StringUtils.hasText(name), Link::getName, name)
                .eq(StringUtils.hasText(status), Link::getStatus, status)
                .page(pageQuery.toPage(pageQuery.getPageNo(), pageQuery.getPageSize()));
        List<Link> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return ResponseResult.okEmptyResult();
        }
        List<LinkVo> categoryVoList = BeanCopyUtils.copyBeanList(records, LinkVo.class);
        return ResponseResult.okResult(new PageVo<>(categoryVoList, records.size()));
    }

    /**
     * 新增友链
     */
    @Override
    public ResponseResult<Object> postLink(LinkDTO linkDTO) {
        Link link = BeanCopyUtils.copyBean(linkDTO, Link.class);
        save(link);
        return ResponseResult.okResult();
    }

    /**
     * 删除友链
     */
    @Override
    public ResponseResult<Object> removeLink(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

    /**
     * 根据友链id查询友链
     */
    @Override
    public ResponseResult<LinkVo> getLinkById(Long id) {
        Link link = getById(id);
        if (Objects.isNull(link)) {
            return ResponseResult.okResult();
        }
        return ResponseResult.okResult(BeanCopyUtils.copyBean(link, LinkVo.class));
    }

    /**
     * 修改友链
     */
    @Override
    public ResponseResult<Object> updateLink(LinkDTO linkDTO) {
        Link link = BeanCopyUtils.copyBean(linkDTO, Link.class);
        updateById(link);
        return ResponseResult.okResult();
    }

    /**
     * 修改友链状态
     */
    @Override
    public ResponseResult<Object> updateCategoryStatus(LinkDTO linkDTO) {
        Link link = BeanCopyUtils.copyBean(linkDTO, Link.class);
        updateById(link);
        return ResponseResult.okResult();
    }
}
