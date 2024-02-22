package com.xiaoqian.common.constants;

public class RedisConstants {
    /**
     * 登录用户前缀
     */
    public static final String REDIS_LOGIN_USER_PREFIX = "login-user:";

    /**
     * 登录用户存活有效期
     */
    public static final Long REDIS_LOGIN_USER_ALIVE_TIME = 24 * 60 * 60 * 1000L;

    /**
     * 用户浏览量 Key(hash)
     */
    public static final String VIEW_COUNT_KEY = "view:count";
}
