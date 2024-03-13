package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.dto.RoleDTO;
import com.xiaoqian.common.domain.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoqian.common.domain.vo.PageVo;
import com.xiaoqian.common.domain.vo.RoleVo;
import com.xiaoqian.common.query.PageQuery;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author QianCCC
 * @since 2024-02-27
 */
public interface IRoleService extends IService<Role> {

    List<String> queryRoleInfoByUserId(Long userId);

    ResponseResult<PageVo<RoleVo>> queryRolePage(PageQuery query, String roleName, String status);

    ResponseResult<Object> updateRoleStatus(RoleDTO role);
}
