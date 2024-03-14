package com.xiaoqian.admin.controller;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.CategoryDTO;
import com.xiaoqian.common.domain.dto.LinkDTO;
import com.xiaoqian.common.domain.vo.CategoryVo;
import com.xiaoqian.common.domain.vo.LinkVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.ILinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "友链相关接口")
@RestController
@RequestMapping("/content/link")
@RequiredArgsConstructor
public class LinkController {
    private final ILinkService linkService;

    @ApiOperation("分页查询友链列表")
    @GetMapping("/list")
    public ResponseResult<PageVo<LinkVo>> queryCategoryPage(PageQuery pageQuery,
                                                            @RequestParam(value = "name", required = false) String name,
                                                            @RequestParam(value = "status", required = false) String status) {
        return linkService.queryLinkPage(pageQuery, name, status);
    }

    @ApiOperation("新增友链")
    @PostMapping
    public ResponseResult<Object> postCategory(@RequestBody LinkDTO linkDTO) {
        return linkService.postLink(linkDTO);
    }

    @ApiOperation("删除友链")
    @DeleteMapping("/{id}")
    public ResponseResult<Object> removeCategory(@PathVariable("id") Long id) {
        return linkService.removeLink(id);
    }

    @ApiOperation("根据id查询友链")
    @GetMapping("/{id}")
    public ResponseResult<LinkVo> getCategoryById(@PathVariable("id") Long id) {
        return linkService.getLinkById(id);
    }

    @ApiOperation("修改友链")
    @PutMapping
    public ResponseResult<Object> updateCategory(@RequestBody LinkDTO linkDTO) {
        return linkService.updateLink(linkDTO);
    }

    @ApiOperation("修改友链状态")
    @PutMapping("/changeLinkStatus")
    public ResponseResult<Object> updateCategoryStatus(@RequestBody LinkDTO linkDTO) {
        return linkService.updateCategoryStatus(linkDTO);
    }
}
