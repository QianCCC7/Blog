package com.xiaoqian.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("菜单数据信息")
public class MenuVo {
    @ApiModelProperty("菜单 id")
    private Long id;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("父菜单 id")
    private Long parentId;
    @ApiModelProperty("显示顺序")
    private Integer orderNum;
    @ApiModelProperty("路由地址")
    private String path;
    @ApiModelProperty("组件地址")
    private String component;
    @ApiModelProperty("菜单类型(M目录 C菜单 F按钮)")
    private String menuType;
    @ApiModelProperty("菜单是否可见(0显示 1隐藏)")
    private String visible;
    @ApiModelProperty("菜单状态(0正常 1停用)")
    private String status;
    @ApiModelProperty("权限标识")
    private String perms;
    @ApiModelProperty("权限图标")
    private String icon;
    @ApiModelProperty("是否为外链")
    private Integer isFrame;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("子菜单信息")
    private List<MenuVo> children;
}
