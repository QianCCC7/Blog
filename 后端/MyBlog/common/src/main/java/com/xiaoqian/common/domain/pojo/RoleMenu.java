package com.xiaoqian.common.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author xiaoqian
 * @since 2024-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_menu")
@ApiModel(value="SysRoleMenu对象", description="角色和菜单关联表")
public class RoleMenu implements Serializable {

    @ApiModelProperty(value = "角色ID")
    @TableId(value = "role_id")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    @TableField(value = "menu_id")
    private Long menuId;
}
