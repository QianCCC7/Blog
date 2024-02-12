package com.xiaoqian.common.enums;

import lombok.Getter;

@Getter
public enum RegisterCodeEnum {
    USERNAME_NOT_NULL(510, "用户名不能为空"),
    NICKNAME_NOT_NULL(511, "用户昵称不能为空"),
    PASSWORD_NOT_NULL(512, "用户密码不能为空"),
    EMAIL_NOT_NULL(513, "用户邮箱不能为空"),

    USERNAME_EXIST(514,"用户名已存在"),
    NICKNAME_EXIST(515, "用户昵称已存在"),
    EMAIL_EXIST(516, "邮箱已存在"),
    ;
    int code;
    String msg;

    RegisterCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
