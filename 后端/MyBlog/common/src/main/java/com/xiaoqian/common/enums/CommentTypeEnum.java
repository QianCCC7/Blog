package com.xiaoqian.common.enums;

import lombok.Getter;

@Getter
public enum CommentTypeEnum {
    ARTICLE_COMMENT(0, "文章评论"),
    LINK_COMMENT(1, "友链评论"),
    ;
    int type;
    String desc;
    CommentTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
