package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqian.common.constants.SystemConstants;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Comment;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.domain.vo.CommentVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.mapper.CommentMapper;
import com.xiaoqian.common.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.service.IUserService;
import com.xiaoqian.common.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author QianCCC
 * @since 2024-02-04
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    private final IUserService userService;

    /**
     * 分页查询评论列表
     */
    @Override
    public ResponseResult<PageVo<CommentVo>> queryCommentList(Long articleId, Integer pageNo, Integer pageSize) {
        // 1. 分页查询文章对应的所有根评论
        Page<Comment> page = lambdaQuery()
                .eq(Comment::getArticleId, articleId)
                .eq(Comment::getRootId, SystemConstants.ROOT_COMMENT_SIGN)
                .page(new Page<>(pageNo, pageSize));
        // 2. 填充数据
        List<Comment> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return ResponseResult.okEmptyResult();
        }
        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(records, CommentVo.class);
        for (CommentVo vo : commentVoList) {
            User user = userService.getById(vo.getCreateBy());
            User toCommentUser = userService.getById(vo.getToCommentUserId());
            vo.setUsername(user != null ? user.getUserName() : null);
            vo.setToCommentUserName(toCommentUser != null ? toCommentUser.getUserName() : null);
        }
        return ResponseResult.okResult(new PageVo<>(commentVoList, records.size()));
    }
}
