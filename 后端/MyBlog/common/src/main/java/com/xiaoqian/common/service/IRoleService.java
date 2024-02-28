package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
