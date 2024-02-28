package com.xiaoqian.common.mapper;

import com.xiaoqian.common.domain.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author QianCCC
 * @since 2024-02-27
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> queryRoleInfoByUserId(@Param("userId") Long userId);

}
