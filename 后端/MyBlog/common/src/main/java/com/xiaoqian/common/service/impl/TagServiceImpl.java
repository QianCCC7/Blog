package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.TagDTO;
import com.xiaoqian.common.domain.pojo.Tag;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.domain.vo.TagVo;
import com.xiaoqian.common.enums.HttpCodeEnum;
import com.xiaoqian.common.exception.SystemException;
import com.xiaoqian.common.exception.TagException;
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

    /**
     * 新增标签
     */
    @Override
    public ResponseResult<Object> addTag(TagDTO tag) {
        if (tag == null || !StringUtils.hasText(tag.getName())
                || !StringUtils.hasText(tag.getRemark())) {
            throw new TagException(HttpCodeEnum.TAG_NOT_NULL);
        }
        Tag copyBean = BeanCopyUtils.copyBean(tag, Tag.class);
        save(copyBean);
        return ResponseResult.okResult();
    }

    /**
     * 删除标签
     */
    @Override
    public ResponseResult<Object> deleteTag(Long tagId) {
        if (tagId == null) {
            throw new SystemException(HttpCodeEnum.SYSTEM_ERROR, "标签id为空");
        }
        removeById(tagId);
        return ResponseResult.okResult();
    }

    /**
     * 根据id查询指定标签
     */
    @Override
    public ResponseResult<TagVo> getTagInfo(Long tagId) {
        if (tagId == null) {
            throw new SystemException(HttpCodeEnum.SYSTEM_ERROR, "标签id为空");
        }
        Tag tag = getById(tagId);
        return ResponseResult.okResult(BeanCopyUtils.copyBean(tag, TagVo.class));
    }

    /**
     * 修改标签
     */
    @Override
    public ResponseResult<Object> updateTag(TagDTO tag) {
        if (tag == null || !StringUtils.hasText(tag.getName())
                || !StringUtils.hasText(tag.getRemark())) {
            throw new TagException(HttpCodeEnum.TAG_NOT_NULL);
        }
        updateById(BeanCopyUtils.copyBean(tag, Tag.class));
        return ResponseResult.okResult();
    }

    /**
     * 写博文时需要查询所有可用的文章分类
     */
    @Override
    public List<Tag> queryAllTags() {
        return list();
    }
}
