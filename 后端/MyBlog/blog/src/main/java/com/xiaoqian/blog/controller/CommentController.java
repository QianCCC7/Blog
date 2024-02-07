package com.xiaoqian.blog.controller;


import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.CommentDTO;
import com.xiaoqian.common.domain.vo.CommentVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.query.PageQuery;
import com.xiaoqian.common.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
                                                              PageQuery pageQuery) {
        return commentService.queryCommentList(articleId, pageQuery);
    }

    @ApiOperation("发送评论")
    @PostMapping
    public ResponseResult<Object> postComment(@RequestBody CommentDTO commentDTO) {
        return commentService.postComment(commentDTO);
    }
}
