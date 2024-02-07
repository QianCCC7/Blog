package com.xiaoqian.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoqian.common.constants.SystemConstants;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Comment;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.domain.vo.CommentVo;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.mapper.CommentMapper;
import com.xiaoqian.common.query.PageQuery;
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
    public ResponseResult<PageVo<CommentVo>> queryCommentList(Long articleId, PageQuery pageQuery) {
        // 1. 分页查询文章对应的所有根评论
        Page<Comment> page = lambdaQuery()
                .eq(Comment::getArticleId, articleId)
                .eq(Comment::getRootId, SystemConstants.ROOT_COMMENT_SIGN)
                .page(pageQuery.toPage(pageQuery.getPageNo(), pageQuery.getPageSize()));

        List<Comment> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return ResponseResult.okEmptyResult();
        }
        return ResponseResult.okResult(new PageVo<>(getCommentVoList(records), records.size()));
    }

    /**
     * 递归查询对应评论的子评论
     * @param rootCommentId 根评论 id
     */
    @SuppressWarnings("unchecked")
    private List<CommentVo> getCommentChildren(Long rootCommentId) {
        List<Comment> commentList = lambdaQuery()
                .eq(Comment::getRootId, rootCommentId)
                .orderByDesc(Comment::getCreateTime) // 根据时间降序查询
                .list();
        return getCommentVoList(commentList);
    }

    /**
     * 根据评论列表获取其对应的 vo列表数据
     */
    public List<CommentVo> getCommentVoList(List<Comment> commentList) {
        List<CommentVo> childrenComments = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        for (CommentVo vo : childrenComments) {
            // 1. 封装 vo用户名数据
            User user = userService.getById(vo.getCreateBy());
            User toCommentUser = userService.getById(vo.getToCommentUserId());
            vo.setUsername(user != null ? user.getUserName() : null);
            vo.setToCommentUserName(toCommentUser != null ? toCommentUser.getUserName() : null);
            // 2. 查询对应根评论的子评论
            List<CommentVo> children = getCommentChildren(vo.getId());
            if (!CollectionUtils.isEmpty(children)) {
                vo.setChildren(children);
            }
        }
        return childrenComments;
    }
}
