package com.xiaoqian.blog.controller;


import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.vo.CommentVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author QianCCC
 * @since 2024-02-04
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论相关接口")
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;

    @ApiOperation("分页查询评论列表")
    @GetMapping("/commentList")
    public ResponseResult<PageVo<CommentVo>> queryCommentList(@RequestParam("articleId") Long articleId,
                                                              @RequestParam("pageNo") Integer pageNo,
                                                              @RequestParam("pageSize") Integer pageSize) {
        return commentService.queryCommentList(articleId, pageNo, pageSize);
    }
}
