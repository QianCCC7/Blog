package com.xiaoqian.common.service.impl;

import com.xiaoqian.common.constants.SystemConstants;
import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Link;
import com.xiaoqian.common.domain.vo.LinkVo;
import com.xiaoqian.common.mapper.LinkMapper;
import com.xiaoqian.common.service.ILinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoqian.common.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
        return null;
    }
}
