package com.xiaoqian.common.constants;

public class SystemConstants {
    /**
     *  文章是草稿
     */
    public static final Integer ARTICLE_STATUS_DRAFT = 1;

    /**
     *  文章是正常分布状态
     */
    public static final Integer ARTICLE_STATUS_NORMAL = 0;

    /**
     * 分类可以正常使用的状态
     */
    public static final Integer CATEGORY_STATUS_NORMAL = 0;

    /**
     * 分类禁用的状态
     */
    public static final Integer CATEGORY_STATUS_DISABLED = 0;

    /**
     * 友链是审核通过状态
     */
    public static final Integer LINK_STATUS_NORMAL = 0;

    /**
     * 根评论标识
     */
    public static final Integer ROOT_COMMENT_SIGN = -1;

    /**
     * 菜单类型 C：菜单
     */
    public static final String MENU_TYPE_C = "C";

    /**
     * 菜单类型 F：按钮
     */
    public static final String MENU_TYPE_F = "F";

    /**
     * 菜单类型 M：目录
     */
    public static final String MENU_TYPE_M = "M";

    /**
     * 菜单状态
     */
    public static final Integer MENU_STATUS_NORMAL = 0;

    /**
     * 是否为管理员
     */
    public static final String IS_ADMIN = "1";

    /**
     * 角色状态（0正常 1停用）
     */
    public static final String ROLE_STATUS_NORMAL = "0";
    public static final String ROLE_STATUS_DISABLED = "1";
}
