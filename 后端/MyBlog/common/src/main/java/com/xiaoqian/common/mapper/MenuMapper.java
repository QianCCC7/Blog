package com.xiaoqian.common.mapper;

import com.xiaoqian.common.domain.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author QianCCC
 * @since 2024-02-27
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> queryMenuInfoByRoleIds(@Param("roleIds") List<Long> roleIds);

    List<Menu> queryMenuInfoByRoleIds2(@Param("roleIds") List<Long> roleIds);
}
