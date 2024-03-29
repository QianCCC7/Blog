package com.xiaoqian.admin.controller;


import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.TagDTO;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.domain.vo.TagVo;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author QianCCC
 * @since 2024-02-28
 */
@RestController
@RequestMapping("/content/tag")
@RequiredArgsConstructor
@Api(tags = "标签相关接口")
public class TagController {
    private final ITagService tagService;

    @ApiOperation("分页查询标签")
    @GetMapping("/list")
    public ResponseResult<PageVo<TagVo>> queryTagPage(PageQuery pageQuery,
                                                      TagDTO tag) {
        return tagService.queryTagPage(pageQuery, tag);
    }

    @ApiOperation("新增标签")
    @PostMapping
    public ResponseResult<Object> addTag(@RequestBody TagDTO tag) {
        return tagService.addTag(tag);
    }

    @ApiOperation("删除标签")
    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteTag(@PathVariable("id") Long tagId) {
        return tagService.deleteTag(tagId);
    }

    @ApiOperation("根据id查询指定标签")
    @GetMapping("/{id}")
    public ResponseResult<TagVo> getTagInfo(@PathVariable("id") Long tagId) {
        return tagService.getTagInfo(tagId);
    }

    @ApiOperation("修改标签")
    @PutMapping()
    public ResponseResult<Object> updateTag(@RequestBody TagDTO tag) {
        return tagService.updateTag(tag);
    }
}
