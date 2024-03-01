package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.TagDTO;
import com.xiaoqian.common.domain.pojo.Tag;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.domain.vo.TagVo;
import com.xiaoqian.common.mapper.TagMapper;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author QianCCC
 * @since 2024-02-28
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    /**
     * 分页查询标签
     */
    @Override
    public ResponseResult<PageVo<TagVo>> queryTagPage(PageQuery pageQuery, TagDTO tag) {
        Page<Tag> page = lambdaQuery()
                .eq(Objects.nonNull(tag) && StringUtils.hasText(tag.getName()), Tag::getName, tag.getName())
                .eq(StringUtils.hasText(tag.getRemark()), Tag::getRemark, tag.getRemark())
                .page(pageQuery.toPage(pageQuery.getPageNo(), pageQuery.getPageSize()));
        List<Tag> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return ResponseResult.okEmptyResult();
        }
        List<TagVo> tagVoList = BeanCopyUtils.copyBeanList(records, TagVo.class);
        return ResponseResult.okResult(new PageVo<>(tagVoList, records.size()));
    }
}
