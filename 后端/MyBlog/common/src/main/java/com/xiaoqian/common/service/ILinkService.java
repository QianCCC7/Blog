package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.pojo.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.vo.LinkVo;

import java.util.List;

/**
 * <p>
 * 友链 服务类
 * </p>
 *
 * @author QianCCC
 * @since 2024-01-27
 */
public interface ILinkService extends IService<Link> {

    ResponseResult<List<LinkVo>> getAllLink();
}
