package com.xiaoqian.common.service;

import com.xiaoqian.common.domain.dto.UserDTO;
import com.xiaoqian.common.domain.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户和角色关联表 服务类
 * </p>
 *
 * @author xiaoqian
 * @since 2024-03-14
 */
public interface IUserRoleService extends IService<UserRole> {

    void addUserRole(UserDTO userDTO);
}
