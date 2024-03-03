package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.TagDTO;
import com.xiaoqian.common.domain.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.domain.vo.TagVo;
import com.xiaoqian.common.query.PageQuery;

import java.util.List;

/**
 * <p>
 * 标签 服务类
 * </p>
 *
 * @author QianCCC
 * @since 2024-02-28
 */
public interface ITagService extends IService<Tag> {

    ResponseResult<PageVo<TagVo>> queryTagPage(PageQuery pageQuery, TagDTO tag);

    ResponseResult<Object> addTag(TagDTO tag);

    ResponseResult<Object> deleteTag(Long tagId);

    ResponseResult<TagVo> getTagInfo(Long tagId);

    ResponseResult<Object> updateTag(TagDTO tag);

    List<Tag> queryAllTags();
}
